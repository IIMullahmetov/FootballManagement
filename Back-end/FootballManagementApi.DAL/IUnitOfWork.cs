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
		
		IMatchRepository GetMatchRepository();

		IPlayerRepository GetPlayerRepository();

		IPostRepository GetPostRepository();

		ITeamRepository GetTeamRepository();

		ITourneyRepository GetTourneyRepository();

		ITourneyTeamRepository GetTourneyTeamRepository();

		IFileRepository GetFileRepository();

        IRegistrationRepository GetRegistrationRepository();

		IBlogRepository GetBlogRepository();

		Task SaveChangesAsync();
	}
}
