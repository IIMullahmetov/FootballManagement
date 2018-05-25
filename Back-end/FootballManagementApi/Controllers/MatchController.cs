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
using FootballManagementApi.MatchRequests;
using FootballManagementApi.MatchResponses;
using FootballManagementApi.Resources;
using FootballManagementApi.Responses;
using Newtonsoft.Json;

namespace FootballManagementApi.Controllers
{
	public class MatchController : BaseController
	{
		public MatchController(IUnitOfWork unitOfWork) : base(unitOfWork)
		{
		}
        /// <summary>
        /// Получение списка матчей
        /// </summary>
        /// <param name="status">статус матча</param>
        /// <param name="page">номер странцы</param>
        /// <param name="size">размер страницы</param>
        /// <returns></returns>
		[HttpGet]
		[Route("match/get_list")]
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
					StartDt = m.StartDt,
					Status = m.Status, 
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
        /// <summary>
        /// Получение матча по идентификатору
        /// </summary>
        /// <param name="id">идентификатор матча</param>
        /// <returns></returns>
		[HttpGet]
		[Route("match/get/{id:int}")]
		public async Task<IHttpActionResult> GetAsync(int id)
		{
			DAL.Models.Match match = await UnitOfWork.GetMatchRepository().SelectByIdAsync(id)
				?? throw new ActionCannotBeExecutedException(ExceptionMessages.MatchNotFound);

			GetResponse response = new GetResponse
			{
				Id = match.Id,
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
					Players = match.Players.Where(p => p.TeamId == match.GuestId).Select(p => new GetResponsePlayer
					{
						Id = p.Id,
						FisrtName = p.FirstName,
						LastName = p.LastName,
						Number = p.Number
					})
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
					Players = match.Players.Where(p => p.TeamId == match.HomeId).Select(p => new GetResponsePlayer
					{
						Id = p.Id,
						FisrtName = p.FirstName,
						LastName = p.LastName,
						Number = p.Number
					})
				}
			};

			return Ok(response);
		}
        /// <summary>
        /// Добавление матча в турнир
        /// </summary>
        /// <param name="id">идентификатор турнира</param>
        /// <param name="request">данные о матче</param>
        /// <returns></returns>
		[HttpPost]
		[Route("tourney/{id:int}/match/create")]
		public async Task<IHttpActionResult> CreateAsync([FromUri]int id, [FromBody]CreateRequest request)
		{
			DAL.Models.Tourney tourney = await UnitOfWork.GetTourneyRepository().SelectByIdAsync(id)
				?? throw new ActionCannotBeExecutedException(ExceptionMessages.TourneyNotFound);

			if (request.HomeId == request.GuestId)
			{
				throw new ActionCannotBeExecutedException(ExceptionMessages.TeamsMustBeDifferent);
			}

			//if (tourney.EndDt < DateTime.Now)
			//{
			//	throw new ActionCannotBeExecutedException(ExceptionMessages.TourenyFinished);
			//}

			if (request.GuestPlayers.Distinct().Count() != 11 || request.HomePlayers.Distinct().Count() != 11)
			{
				throw new ActionCannotBeExecutedException(ExceptionMessages.InvalidPlayersCount);
			}

			ITeamRepository teamRepo = UnitOfWork.GetTeamRepository();
			SelectOptions<DAL.Models.Team> teamSelectOption = new SelectOptions<DAL.Models.Team>();
			teamSelectOption.Includes.Add(t => t.Players);

			DAL.Models.Team homeTeam = await teamRepo.SelectByIdAsync(request.HomeId, teamSelectOption)
				?? throw new ActionCannotBeExecutedException(ExceptionMessages.TeamNotFound + $" Id : {request.HomeId}");
			ValidateTeam(tourney, homeTeam);

			DAL.Models.Team guestTeam = await teamRepo.SelectByIdAsync(request.GuestId, teamSelectOption)
				?? throw new ActionCannotBeExecutedException(ExceptionMessages.TeamNotFound + $" Id : {request.GuestId}");
			ValidateTeam(tourney, guestTeam);

			ValidatePlayers(homeTeam, request.HomePlayers);
			ValidatePlayers(guestTeam, request.GuestPlayers);

			DAL.Models.Match match = new DAL.Models.Match
			{
				StartDt = request.StartDt,
				Home = homeTeam,
				Guest = guestTeam,
				Status = MatchStatus.Started,
				Tourney = tourney,
				Players = homeTeam.Players.Where(p => request.HomePlayers.Contains(p.Id))
					.Concat(guestTeam.Players.Where(p => request.GuestPlayers.Contains(p.Id))).ToList()
			};
			match.EndDt = match.StartDt.AddMinutes(90);

			UnitOfWork.GetMatchRepository().Insert(match);

			await UnitOfWork.SaveChangesAsync();
			
			return Ok();
		}

		private void ValidatePlayers(DAL.Models.Team team, IEnumerable<int> players)
		{
			foreach (int playerId in players)
			{
				if (!team.Players.Any(p => p.Id == playerId))
				{
					throw new ActionCannotBeExecutedException(ExceptionMessages.PlayerNotFound + $"in Team by Id : {team.Id} PlayerId : {playerId}");
				}
			}
		}

		private void ValidateTeam(DAL.Models.Tourney tourney, DAL.Models.Team team)
		{
			if (!tourney.Teams.Any(t => t.TeamId == team.Id))
			{
				throw new ActionCannotBeExecutedException(ExceptionMessages.TeamNotFound + $"in Tourney by Id = {tourney.Id} TeamId : {team.Id}");
			}
		}

        /// <summary>
        /// добавление кол-во голов к матчу
        /// </summary>
        /// <param name="id">идентификатор матча</param>
        /// <param name="request">данные о голах за матч</param>
        /// <returns></returns>
		[HttpPost]
		[Route("match/{id:int}/score")]
		[Auth.Authorize(Role.Admin)]
		public async Task<IHttpActionResult> ScoreAsync(int id, ScoreRequest request)
		{
			DAL.Models.Match match = await UnitOfWork.GetMatchRepository().SelectByIdAsync(id)
				?? throw new ActionCannotBeExecutedException(ExceptionMessages.MatchNotFound);
			if (!match.Players.Any(p => p.Id != request.AuthorId))
			{
				throw new ActionCannotBeExecutedException(ExceptionMessages.PlayerNotFound + $" In match by Id : {id} UserId : {request.AuthorId}");
			}
			
			IPlayerRepository playerRepo = UnitOfWork.GetPlayerRepository();
			DAL.Models.Player author = await playerRepo.SelectByIdAsync(request.AuthorId)
				?? throw new ActionCannotBeExecutedException(ExceptionMessages.PlayerNotFound);
			DAL.Models.Player assistant = null;

			if (request.AssistantId.HasValue)
			{
				if (!match.Players.Any(p => p.Id != request.AssistantId.Value))
				{
					throw new ActionCannotBeExecutedException(ExceptionMessages.PlayerNotFound + $" In match by Id : {id} UserId : {request.AuthorId}");
				}
				assistant = await playerRepo.SelectByIdAsync(request.AssistantId.Value)
					?? throw new ActionCannotBeExecutedException(ExceptionMessages.PlayerNotFound);
				if (assistant.TeamId != author.TeamId)
				{
					throw new ActionCannotBeExecutedException(ExceptionMessages.TeamsMustBeSame);
				}
			}

			match.Goals.Add(new DAL.Models.Goal
			{
				Author = author,
				Assistant = assistant,
				GoalDt = DateTimeOffset.Now,
				Team = author.Team
			});
			await UnitOfWork.SaveChangesAsync();
			return Ok();
		}
	}
}
