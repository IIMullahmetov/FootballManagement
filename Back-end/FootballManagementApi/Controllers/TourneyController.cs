using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using FootballManagementApi.DAL;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.DAL.Repositories;
using FootballManagementApi.Enums;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.Resources;
using FootballManagementApi.Responses;
using FootballManagementApi.TourneyResponses;
using Newtonsoft.Json;
using Swashbuckle.Swagger.Annotations;

namespace FootballManagementApi.Controllers
{
	public class TourneyController : BaseController
	{
		public TourneyController(IUnitOfWork unitOfWork) : base(unitOfWork) { }

        /// <summary>
        /// Получение списка турниров
        /// </summary>
        /// <param name="page">номер страницы</param>
        /// <param name="size">размер страницы</param>
        /// <returns></returns>
	    [HttpGet]
	    [Route("tourney/get_list")]
	    [SwaggerResponse(200, Type = typeof(GetListResponse))]
	    public async Task<IHttpActionResult> GetListAsync([FromUri] int page = 0, [FromUri] int size = 10)
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
        /// <summary>
        /// Получение турнира по идентификатору
        /// </summary>
        /// <param name="id">Идентификатор турнира</param>
        /// <returns></returns>
	    [HttpGet]
		[Route("tourney/get/{id:int}")]
		[SwaggerResponse(200, Type = typeof(GetResponse))]
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
				Items = tourney.Teams.Select(t => new GetItem
				{
					Id = t.TeamId,
					Image = t.Team.Logotype,
					Name = t.Team.Name,
					Position = GetScoreOntourney(team: t.Team, tourney: tourney),
					Status = t.Status
				})
			};
			return Ok(response);
		}
		/// <summary>
        /// Добавление команд к турниру с правами админа
        /// </summary>
        /// <param name="id">Идентификатор турнира</param>
        /// <param name="request">Команды</param>
        /// <returns></returns>
		[HttpPost]
		[Route("tourney/{id:int}/add_teams")]
		[Auth.Authorize(role: Role.Admin)]
		public async Task<IHttpActionResult> AddTeamAsync(int id, [FromBody]AddTeamRequest request)
		{
			if (request.Teams.Distinct().Count() != 8)
			{
				throw new ActionCannotBeExecutedException(ExceptionMessages.InvalidTeamsCount);
			}

			Tourney tourney = await UnitOfWork.GetTourneyRepository().SelectByIdAsync(id)
				?? throw new ActionCannotBeExecutedException(ExceptionMessages.TourneyNotFound);

			if (tourney.EndDt < DateTime.Now)
			{
				throw new ActionCannotBeExecutedException(ExceptionMessages.TourenyFinished);
			}

			IEnumerable<Team> teams = await UnitOfWork.GetTeamRepository().SelectAsync(t => request.Teams.Contains(t.Id));
			foreach(Team team in teams)
			{
				tourney.Teams.Add(new TourneyTeam
				{
					Team = team,
					Status = TourneyTeamStatus.Participating
				});
			}

			await UnitOfWork.SaveChangesAsync();
			return Ok();
		}

		private async Task ValidateTeamsAsync(IEnumerable<int> teams)
		{
			DAL.Repositories.ITeamRepository repo = UnitOfWork.GetTeamRepository();

			foreach(int id in teams)
			{
				if (!(await repo.AnyAsync(t => t.Id == id)))
				{
					throw new ActionCannotBeExecutedException(ExceptionMessages.TeamNotFound + $" Id : {id}");
				}
			}
		}

		public class AddTeamRequest
		{
			[JsonProperty("teams")]
			public IEnumerable<int> Teams { get; set; }
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

            /// <summary>
            /// Подсчет очков команды в турнире
            /// </summary>
            /// <param name="team">команда</param>
            /// <param name="tourney">тунир</param>
            /// <returns></returns>
		private int GetScoreOntourney(Team team, Tourney tourney)
		{
		    int score = 0;
            IEnumerable<Match> matches = tourney.Matches.Where(t => t.GuestId == team.Id | t.HomeId == team.Id);
            foreach (var teamMatch in matches)
            {
                int teamGoals = teamMatch.Goals.Select(g => g.TeamId == team.Id).Count();
                int goals = teamMatch.Goals.Select(g => g.TeamId != team.Id).Count();
                if (teamGoals == goals)
                {
                    score = +1;
                }
                else if (teamGoals > goals)
                {
                    score += 3;
                }
            }
            return score;
		}
	}
}
