using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using FootballManagementApi.DAL;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;

namespace FootballManagementApi.Controllers
{
	[RoutePrefix("auth")]
	public class AuthController : BaseController
	{
		public AuthController(IUnitOfWorkFactory unitOfWorkFactory) : base(unitOfWorkFactory.GetUnitOfWork())
		{

		}
		
		[Route("login")]
		public async Task<IHttpActionResult> LoginAsync()
		{
			throw new ActionCannotBeExecutedException("");
		}
	}
}
