using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using System.Web.Http;
using FootballManagementApi.DAL;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;

namespace FootballManagementApi.Controllers
{
	[RoutePrefix("file")]
	public class FileController : BaseController
	{
		public FileController(IUnitOfWork unitOfWork) : base(unitOfWork)
		{
		}

		[HttpPost]
		[Route("upload")]
		public async Task<IHttpActionResult> UploadFilesAsync()
		{
			User user = await GetCurrentUserAsync() ?? throw new ActionForbiddenException();

			return Ok();
		}

		[HttpGet]
		[Route("download")]
		public HttpResponseMessage Post(Guid guid)
		{
			HttpResponseMessage result = new HttpResponseMessage(HttpStatusCode.OK);
			Stream stream = new MemoryStream(new byte[5000]);
			result.Content = new StreamContent(stream);
			result.Content.Headers.ContentType = new MediaTypeHeaderValue("application/octet-stream");
			return result;
		}
	}
}
