using FootballManagementApi.DAL;
using FootballManagementApi.Responses;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;

namespace FootballManagementApi.Controllers
{
    public abstract class BaseController : ApiController
    {
		private IUnitOfWork _unitOfWork;

		protected IUnitOfWork UnitOfWork => _unitOfWork;

		protected BaseController(IUnitOfWork unitOfWork)
		{
			_unitOfWork = unitOfWork;
		}

		protected IHttpActionResult Ok()
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

    }
}
