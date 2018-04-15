using System;
using System.Threading.Tasks;
using FootballManagementApi.DAL.Repositories;

namespace FootballManagementApi.DAL
{
	public interface IUnitOfWork : IDisposable
	{
		IUserRepository GetUserRepository();

		ICommentRepository GetCommentRepository();

		IGoalRepository GetGoalRepository();

		ILeagueRepository GetLeagueRepository();

		ILeagueTeamRepository GetLeagueTeamRepository();

		IMatchRepository GetMatchRepository();

		IPlayerRepository GetPlayerRepository();

		IPostRepository GetPostRepository();

		ITeamRepository GetTeamRepository();

		ITourneyRepository GetTourneyRepository();

		ITourneyTeamRepository GetTourneyTeamRepository();

		IFileRepository GetFileRepository();

		Task SaveChangesAsync();
	}
}
