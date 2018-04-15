using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using FootballManagementApi.DAL;

namespace FootballManagementApi.Controllers
{
	[RoutePrefix("tourney")]
	public class TourneyController : BaseController
	{
		public TourneyController(IUnitOfWork unitOfWork) : base(unitOfWork) { }


	}
}
