using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using System.Web.Http;
using FootballManagementApi.DAL;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.DAL.Repositories;
using FootballManagementApi.FileStorage;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.Helpers;

namespace FootballManagementApi.Controllers
{
	[RoutePrefix("file")]
	public class FileController : BaseController
	{
		private IFileManager _fileManager;

		public FileController(IUnitOfWork unitOfWork, IFileManager fileManager) : base(unitOfWork)
		{
			_fileManager = fileManager;
		}

		[HttpPost]
		[Route("upload")]
		public async Task<IHttpActionResult> UploadFilesAsync()
		{
			User user = await GetCurrentUserAsync(); //?? throw new ActionForbiddenException();
			Dictionary<string, byte[]> files = await ReadAsMultipartAsync();
			IFileRepository repo = UnitOfWork.GetFileRepository();
			foreach (KeyValuePair<string, byte[]> keyValuePair in files)
			{
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
			}
			await UnitOfWork.SaveChangesAsync();
			return Ok();
		}

		[HttpGet]
		[Route("download")]
		public async Task<HttpResponseMessage> DownloadAsync(Guid guid)
		{
			IFileRepository repo = UnitOfWork.GetFileRepository();
			File file = await repo.SelectFirstOrDefaultAsync(f => f.Guid == guid)
				?? throw new ActionCannotBeExecutedException("Not found");
			string path = PathHelper.GeneratePath(guid);

			byte[] bytes = await _fileManager.GetFileAsync(path, file.Size)
				?? throw new ActionCannotBeExecutedException("Not found");

			HttpResponseMessage result = new HttpResponseMessage(HttpStatusCode.OK);
			result.Content = new StreamContent(new System.IO.MemoryStream(bytes));
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
