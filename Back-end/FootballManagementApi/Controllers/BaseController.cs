using FootballManagementApi.DAL;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.Responses;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http;

namespace FootballManagementApi.Controllers
{
    public abstract class BaseController : ApiController
    {
        protected const string fileCache = "FileCache";
        protected const string dbFileCache = "DbFileCache";
		public static readonly Random Random = new Random();

		private IUnitOfWork _unitOfWork;

        protected IUnitOfWork UnitOfWork => _unitOfWork;

        protected BaseController(IUnitOfWork unitOfWork)
        {
            _unitOfWork = unitOfWork;
        }

        protected new IHttpActionResult Ok()
        {
            return base.Ok();
        }

        protected IHttpActionResult Ok(ResponseBase response)
        {
            return base.Ok(response);
        }

        protected IHttpActionResult BadRequest()
        {
            return base.BadRequest();
        }

        protected IHttpActionResult BadRequest(string message)
        {
            return base.BadRequest(message);
        }


        protected async Task<Dictionary<string, byte[]>> ReadAsMultipartAsync()
        {
            if (!Request.Content.IsMimeMultipartContent())
            {
                throw new HttpResponseException(HttpStatusCode.UnsupportedMediaType);
            }
            HttpRequest httpRequest = HttpContext.Current.Request;
            Dictionary<string, byte[]> files = new Dictionary<string, byte[]>();

            foreach (string fileName in httpRequest.Files.Keys)
            {
                HttpPostedFile file = httpRequest.Files[fileName];
                files.Add(fileName, await new StreamContent(file.InputStream).ReadAsByteArrayAsync());
            }

            return files;
        }

        //TODO Доделать 
        protected async Task<User> GetCurrentUserAsync()
        {
            int? id = ((User as Auth.Principal)?.Identity as Auth.Identity)?.Id;
            User user = null;

            if (id != null)
            {
                user = await UnitOfWork.GetUserRepository()
                    .SelectFirstOrDefaultAsync(u => u.Id == id && u.Status == Enums.UserStatus.Active);
            }

            return user;
        }
    }
}
