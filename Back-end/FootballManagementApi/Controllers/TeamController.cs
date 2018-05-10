using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using FootballManagementApi.DAL;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.Resources;
using FootballManagementApi.TeamResponses;
using Newtonsoft.Json;

namespace FootballManagementApi.Controllers
{
	[RoutePrefix("team")]
	public class TeamController : BaseController
	{
		public TeamController(IUnitOfWork unitOfWork) : base(unitOfWork)
		{
		}

		[HttpGet]
		[Route("get/{id:int}")]
		public async Task<IHttpActionResult> GetAsync(int id)
		{
			Team team = await UnitOfWork.GetTeamRepository().SelectByIdAsync(id) 
				?? throw new ActionCannotBeExecutedException(ExceptionMessages.TeamNotFound);

			GetResponse response = new GetResponse
			{
				Id = team.Id,
				Logo = team.Logotype,
				Name = team.Name,
				Players = team.Players.Select(p => new TeamPlayer
				{
					FirstName = p.FirstName,
					Image = p.Image,
					LastName = p.LastName,
					Id = p.Id
				})
			};
			return Ok(response);
		}

		//[HttpPost]
		//[Route("create")]
		//public async Task<IHttpActionResult> CreateAsync([FromBody]CreateRequest1 request)
		//{
		//	Team team = new Team
		//	{
		//		Logotype = request.Guid,
		//		Name = request.Name
		//	};
		//	UnitOfWork.GetTeamRepository().Insert(team);
		//	await UnitOfWork.SaveChangesAsync();
		//	return Ok();
		//}

		//public class CreateRequest1
		//{
		//	public string Name { get; set; }

		//	public Guid Guid { get; set; }
		//}
	}
}
