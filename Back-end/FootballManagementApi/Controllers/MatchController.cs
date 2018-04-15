using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Web.Http;
using FootballManagementApi.DAL;
using FootballManagementApi.DAL.MatchSpecifications;
using FootballManagementApi.DAL.Repositories;
using FootballManagementApi.Enums;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.MatchResponses;
using FootballManagementApi.Resources;
using FootballManagementApi.Responses;

namespace FootballManagementApi.Controllers
{
	[RoutePrefix("match")]
	public class MatchController : BaseController
	{
		public MatchController(IUnitOfWork unitOfWork) : base(unitOfWork)
		{
		}

		[HttpGet]
		[Route("get_list")]
		public async Task<IHttpActionResult> GetListAsync([FromUri]MatchStatus? status = null, [FromUri]int page = 0, [FromUri]int size = 10)
		{
			IMatchRepository repo = UnitOfWork.GetMatchRepository();
			SelectOptions<DAL.Models.Match> options = new SelectOptions<DAL.Models.Match>
			{
				OrderBy = p => p.OrderByDescending(t => t.Id),
				Take = size,
				Skip = page * size
			};
			StatusSpecification specification = new StatusSpecification(status);
			IEnumerable<DAL.Models.Match> result = await repo.SelectAsync(specification = specification, options: options);
			int count = await repo.CountAsync(specification: specification);

			Paging paging = new Paging(count: count, page: page, size: size);

			GetListResponse response = new GetListResponse(paging)
			{
				Items = result.Select(m => new GetListResponseItem
				{
					Id = m.Id,
					Guest = new GetListResponseItemTeam
					{
						Id = m.GuestId,
						City = m.Guest.City,
						Country = m.Guest.County,
						Goals = m.Goals.Count(g => g.TeamId == m.GuestId),
						Name = m.Guest.Name,
						Logotype = m.Guest.Logotype
					},
					Home = new GetListResponseItemTeam
					{
						Id = m.HomeId,
						City = m.Home.City,
						Country = m.Home.County,
						Goals = m.Goals.Count(g => g.TeamId == m.HomeId),
						Name = m.Home.Name,
						Logotype = m.Home.Logotype
					}
				})
			};
			return Ok(response);
		}

		[HttpGet]
		[Route("get/{id:int}")]
		public async Task<IHttpActionResult> GetAsync(int id)
		{
			DAL.Models.Match match = await UnitOfWork.GetMatchRepository().SelectByIdAsync(id)
				?? throw new ActionCannotBeExecutedException(ExceptionMessages.MatchNotFound);

			GetResponse response = new GetResponse
			{
				Id = match.Id,
				LeagueId = match.LeagueId,
				TourneyId = match.TourneyId,
				Guest = new GetResponseTeam
				{
					Id = match.GuestId,
					City = match.Guest.City,
					Country = match.Guest.County,
					Goals = match.Goals.Where(g => g.TeamId == match.GuestId).Select(g => new GetResponseTeamGoal
					{
						AuthorId = g.AuthorId,
						GoalDt = g.GoalDt,
						AssistantId = g.AssistantId
					}),
					Name = match.Guest.Name,
					Logotype = match.Guest.Logotype,
				},
				Home = new GetResponseTeam
				{
					Id = match.HomeId,
					City = match.Home.City,
					Country = match.Home.County,
					Goals = match.Goals.Where(g => g.TeamId == match.HomeId).Select(g => new GetResponseTeamGoal
					{
						AuthorId = g.AuthorId,
						GoalDt = g.GoalDt,
						AssistantId = g.AssistantId
					}),
					Name = match.Home.Name,
					Logotype = match.Home.Logotype,
				}
			};

			return Ok(response);
		}
	}
}
