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
using FootballManagementApi.PlayerResponses;
using FootballManagementApi.Resources;

namespace FootballManagementApi.Controllers
{
	[RoutePrefix("player")]
	public class PlayerController : BaseController
	{
		

		public PlayerController(IUnitOfWork unitOfWork) : base(unitOfWork)
		{

		}

		[HttpGet]
		[Route("get/{id:int}")]
		public async Task<IHttpActionResult> GetAsync(int id)
		{
			Player player = await UnitOfWork.GetPlayerRepository().SelectByIdAsync(id) ?? throw new ActionCannotBeExecutedException(ExceptionMessages.PlayerNotFound);

			GetResponse response = new GetResponse
			{
				Id = player.Id,
				FirstName = player.FirstName,
				LastName = player.LastName,
				TeamId = player.TeamId,
				Team = player.Team.Name,
				Age = DateTime.Now.Year - player.BirthDt.Year,
				Image = player.Image
			};

			return Ok(response);
		}

		//[HttpPost]
		//[Route("create")]
		//public async Task<IHttpActionResult> CreateAsync([FromBody]CreateRequest request)
		//{
		//	int year = Random.Next(1985, 2003);
		//	int month = Random.Next(1, 12);
		//	int day = Random.Next(1, 28);
		//	int count = await UnitOfWork.GetTeamRepository().CountAsync(t => true);

		//	Player player = new Player
		//	{
		//		BirthDt = new DateTime(year, month, day),
		//		FirstName = request.FirstName,
		//		LastName = request.LastName,
		//		Image = request.Guid,
		//		TeamId = Random.Next(1, count),
		//		Number = Random.Next(0, 99),
		//		Status = Enums.PlayerStatus.Active
		//	};
		//	UnitOfWork.GetPlayerRepository().Insert(player);
		//	await UnitOfWork.SaveChangesAsync();
		//	return Ok();
		//}

		//public class CreateRequest
		//{
		//	public string FirstName { get; set; }

		//	public string LastName { get; set; }

		//	public Guid Guid { get; set; }
		//}
	}
}
