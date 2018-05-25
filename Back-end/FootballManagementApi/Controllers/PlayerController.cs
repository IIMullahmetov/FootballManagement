using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web.Http;
using FootballManagementApi.DAL;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.DAL.PlayerSpecifications;
using FootballManagementApi.DAL.Repositories;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.PlayerResponses;
using FootballManagementApi.Resources;
using FootballManagementApi.Responses;
using Swashbuckle.Swagger.Annotations;

namespace FootballManagementApi.Controllers
{
	public class PlayerController : BaseController
	{


		public PlayerController(IUnitOfWork unitOfWork) : base(unitOfWork)
		{

		}

		[HttpGet]
		[Route("player/get/{id:int}")]
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
				Image = player.Image,
				AssistsCount = player.Assists.Count,
				BirthDt = player.BirthDt.ToString("dd-MM-yyyy"),
				MatchesCount = player.Matches.Count,
				GoalsCount = player.Goals.Count
			};

			return Ok(response);
		}

		[HttpGet]
		[Route("player/{id:int}/match/get_list")]
		[SwaggerResponse(System.Net.HttpStatusCode.OK, Type = typeof(MatchGetListResponse))]
		public async Task<IHttpActionResult> MatchGetListAsync(int id, [FromUri]int page = 0, [FromUri]int size = 10, [FromUri]int? tourneyId = null, [FromUri]DateTime? season = null)
		{
			Player player = await UnitOfWork.GetPlayerRepository().SelectByIdAsync(id) ?? throw new ActionCannotBeExecutedException(ExceptionMessages.PlayerNotFound);

			ISpecification<Match> specification = new TourneySpecification(tourneyId)
				.And(new SeasonSpecification(season));

			SelectOptions<Match> option = new SelectOptions<Match>
			{
				Skip = page * size,
				Take = size,
				OrderBy = m => m.OrderByDescending(r => r.StartDt)
			};
			IMatchRepository repo = UnitOfWork.GetMatchRepository();

			IEnumerable<Match> matches = await repo.SelectAsync(specification, option);
			int count = await repo.CountAsync(specification);
			Paging paging = new Paging(count: count, page: page, size: size);

			MatchGetListResponse response = new MatchGetListResponse(paging)
			{
				Items = matches.Select(m => new MathGetListItem
				{
					Id = m.Id,
					Date = m.StartDt.ToString("dd.MM.yyyy"),
					Assists = m.Goals.Count(g => g.AssistantId == player.Id && g.TeamId == player.TeamId),
					AlignmentShots = m.Shots.Count(s => s.TeamId == player.TeamId && s.Type == Enums.ShotType.Alignment),
					CornerShots = m.Shots.Count(s => s.TeamId == player.TeamId && s.Type == Enums.ShotType.Corner),
					GoalShots = m.Shots.Count(s => s.TeamId == player.TeamId && s.Type == Enums.ShotType.Goal),
					Offside = m.Shots.Count(s => s.TeamId == player.TeamId && s.Type == Enums.ShotType.Offside),
					Guest = new MatchGetListTeamInfo
					{
						Id = m.GuestId,
						Name = m.Guest.Name,
					},
					Home = new MatchGetListTeamInfo
					{
						Id = m.HomeId,
						Name = m.Home.Name,
					},
					Goals = m.Goals.Count(g => g.AuthorId == player.Id),
					Minutes = 90,
					RedCards = m.Players.SelectMany(p => p.Cards.Where(c => c.PlayerId == player.Id && c.Type == Enums.CardType.Red)).Count(),
					YellowCards = m.Players.SelectMany(p => p.Cards.Where(c => c.PlayerId == player.Id && c.Type == Enums.CardType.Yellow)).Count(),
					TourneyInfo = new MatchGetListTourneyInfo
					{
						Id = m.TourneyId,
						Name = m.Tourney.Name
					}
				})
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
