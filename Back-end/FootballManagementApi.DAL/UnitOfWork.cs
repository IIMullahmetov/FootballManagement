using System.Data.Entity;
using System.Threading.Tasks;
using FootballManagementApi.DAL.Implementations;
using FootballManagementApi.DAL.Repositories;

namespace FootballManagementApi.DAL
{
	public class UnitOfWork<TContext> : IUnitOfWork where TContext : DbContext, new()
	{
		private bool _isDisposed = false;

		private TContext Context { get; set; }

		public UnitOfWork() => Context = new TContext();

		public void Dispose()
		{
			if (_isDisposed)
			{
				return;
			}

			Context.Dispose();
			_isDisposed = true;
		}

		public Task SaveChangesAsync()
		{
			Context.ChangeTracker.DetectChanges();
			return Context.SaveChangesAsync();
		}

		public IUserRepository GetUserRepository() => new UserRepository(Context);

		public ICommentRepository GetCommentRepository() => new CommentRepository(Context);

		public IGoalRepository GetGoalRepository() => new GoalRepository(Context);

		public ILeagueRepository GetLeagueRepository() => new LeagueRepository(Context);

		public ILeagueTeamRepository GetLeagueTeamRepository() => new LeagueTeamRepository(Context);

		public IMatchRepository GetMatchRepository() => new MatchRepository(Context);

		public IPlayerRepository GetPlayerRepository() => new PlayerRepository(Context);

		public IPostRepository GetPostRepository() => new PostRepository(Context);

		public ITeamRepository GetTeamRepository() => new TeamRepository(Context);

		public ITourneyRepository GetTourneyRepository() => new TourneyRepository(Context);

		public ITourneyTeamRepository GetTourneyTeamRepository() => new TourneyTeamRepository(Context);
	}
}
