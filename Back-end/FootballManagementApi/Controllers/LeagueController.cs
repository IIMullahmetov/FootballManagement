using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using FootballManagementApi.DAL;

namespace FootballManagementApi.Controllers
{
	[RoutePrefix("league")]
	public class LeagueController : BaseController
	{
		public LeagueController(IUnitOfWork unitOfWork) : base(unitOfWork) { }

	}
}
