using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Runtime.Caching;
using System.Threading.Tasks;
using System.Web.Http;
using FootballManagementApi.DAL;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.DAL.Repositories;
using FootballManagementApi.FileResponses;
using FootballManagementApi.FileStorage;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.Helpers;
using FootballManagementApi.Resources;

namespace FootballManagementApi.Controllers
{
    [RoutePrefix("file")]
    public class FileController : BaseController
    {
        private IFileManager _fileManager;
        private MemoryCache _fileCache = MemoryCache.Default;

        public FileController(IUnitOfWork unitOfWork, IFileManager fileManager) : base(unitOfWork)
        {
            _fileManager = fileManager;
        }

        [HttpPost]
        [Route("upload")]
        [Auth.Authorize]
        public async Task<IHttpActionResult> UploadAsync()
        {
            User user = await GetCurrentUserAsync() ?? throw new ActionForbiddenException();
            Dictionary<string, byte[]> files = await ReadAsMultipartAsync();
            KeyValuePair<string, byte[]> keyValuePair = files.FirstOrDefault();
            IFileRepository repo = UnitOfWork.GetFileRepository();
            File file = new File
            {
                CreateDt = DateTimeOffset.Now,
                Guid = Guid.NewGuid(),
                Name = keyValuePair.Key,
                User = user,
                Size = keyValuePair.Value.Length
            };
            string path = PathHelper.GeneratePath(file.Guid);
            await _fileManager.WriteFileAsync(keyValuePair.Value, path);
            repo.Insert(file);
            await UnitOfWork.SaveChangesAsync();
            UploadResponse response = new UploadResponse
            {
                Guid = file.Guid
            };

            return Ok(response);
        }

        [HttpGet]
        [Route("download")]
        public async Task<HttpResponseMessage> DownloadAsync([FromUri]Guid guid)
        {
            IFileRepository repo = UnitOfWork.GetFileRepository();
            File file = _fileCache.Get(guid.ToString()) as File;
            if (file == null)
            {
                file = await repo.SelectFirstOrDefaultAsync(f => f.Guid == guid)
                    ?? throw new ActionCannotBeExecutedException(ExceptionMessages.FileNotFound);
                _fileCache.Add(guid.ToString(), file, DateTimeOffset.UtcNow.AddMinutes(30));
            }

            byte[] bytes = _fileCache.Get(file.Id.ToString()) as byte[];

            if (bytes == null)
            {
                string path = PathHelper.GeneratePath(file.Guid);
                bytes = await _fileManager.GetFileAsync(path, file.Size)
                    ?? throw new ActionCannotBeExecutedException(ExceptionMessages.FileNotFound);
                _fileCache.Add(key: file.Id.ToString(), value: bytes, absoluteExpiration: DateTimeOffset.UtcNow.AddMinutes(30));
            }

            HttpResponseMessage result = new HttpResponseMessage(HttpStatusCode.OK)
            {
                Content = new StreamContent(new System.IO.MemoryStream(bytes))
            };
            //result.Content.Headers.ContentType = new MediaTypeHeaderValue("application/octet-stream");
            result.Content.Headers.ContentDisposition = new ContentDispositionHeaderValue("attachment")
            {
                FileName = file.Name
            };
            result.Content.Headers.ContentType = new MediaTypeHeaderValue("application/octet-stream");
            return result;
        }
    }
}
