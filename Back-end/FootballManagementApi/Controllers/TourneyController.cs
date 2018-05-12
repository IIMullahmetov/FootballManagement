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
using FootballManagementApi.Responses;
using FootballManagementApi.TourneyResponses;

namespace FootballManagementApi.Controllers
{
	[RoutePrefix("tourney")]
	public class TourneyController : BaseController
	{
		public TourneyController(IUnitOfWork unitOfWork) : base(unitOfWork) { }


		[HttpGet]
		[Route("get_list")]
		public async Task<IHttpActionResult> GetListAsync([FromUri]int page = 0, [FromUri]int size = 10)
		{
			SelectOptions<Tourney> options = new SelectOptions<Tourney>
			{
				OrderBy = p => p.OrderByDescending(t => t.Id),
				Take = size,
				Skip = page * size
			};

			IEnumerable<Tourney> results = await UnitOfWork.GetTourneyRepository().SelectAllAsync(options: options);

			Paging paging = new Paging(count: results.Count(), page: page, size: size);

			GetListResponse response = new GetListResponse(paging)
			{
				Items = results.Select(t => new GetListItem
				{
					Id = t.Id,
					Name = t.Name,
					EndDt = t.EndDt,
					StartDt = t.StartDt
				})
			};

			return Ok(response);
		}

		[HttpGet]
		[Route("get/{id:int}")]
		public async Task<IHttpActionResult> GetAsync([FromUri]int id)
		{
			Tourney tourney = await UnitOfWork.GetTourneyRepository().SelectByIdAsync(id)
				?? throw new ActionCannotBeExecutedException(ExceptionMessages.TourneyNotFound);

			GetResponse response = new GetResponse
			{
				Id = tourney.Id,
				Name = tourney.Name,
				StartDt = tourney.StartDt,
				EndDt = tourney.EndDt,
				Items = tourney.Teams.Where(t => t.Status == Enums.TourneyTeamStatus.Participating).Select(t => new GetItems
				{
					Id = t.Id,
					Image = t.Team.Logotype,
					Name = t.Team.Name,
					Position = GetPositionOntourney(team: t.Team)
				})
			};
			return Ok(response);
		}

		//[HttpGet]
		//[Route("create")]
		//public async Task<IHttpActionResult> CreateAsync([FromUri]string request)
		//{
		//	int year = Random.Next(2015, 2017);
		//	int month = Random.Next(1, 12);
		//	int day = Random.Next(1, 28);

		//	Tourney tourney = new Tourney
		//	{
		//		StartDt = new DateTime(year, month, day),
		//		Name = request
		//	};
		//	tourney.EndDt = tourney.StartDt.AddMonths(3);
		//	UnitOfWork.GetTourneyRepository().Insert(tourney);

		//	await UnitOfWork.SaveChangesAsync();

		//	int count = await UnitOfWork.GetTeamRepository().CountAsync(t => true);

		//	List<int> teams = new List<int>();

		//	while (teams.Count < 16)
		//	{
		//		int teamId = Random.Next(1, count);
		//		if (!teams.Contains(teamId))
		//		{
		//			teams.Add(teamId);
		//		}
		//	}
		//	foreach(var v in teams)
		//	{
		//		tourney.Teams.Add(new TourneyTeam
		//		{
		//			Status = Enums.TourneyTeamStatus.Participating,
		//			TeamId = v,
		//			TourneyId = tourney.Id
		//		});
		//	}
		//	await UnitOfWork.SaveChangesAsync();
		//	return Ok();
		//}

		//TODO Подсчитать позицию в турнире
		private int GetPositionOntourney(Team team)
		{
			return 0;
		}
	}
}
