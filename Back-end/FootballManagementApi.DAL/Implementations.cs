using System.Data.Entity;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.DAL.Repositories;

namespace FootballManagementApi.DAL.Implementations
{
	internal class UserRepository : GenericRepository<User>, IUserRepository
	{
		internal UserRepository(DbContext context) : base(context) { }
	}

	internal class CommentRepository : GenericRepository<Comment>, ICommentRepository
	{
		internal CommentRepository(DbContext context) : base(context) { }
	}

	internal class GoalRepository : GenericRepository<Goal>, IGoalRepository
	{
		internal GoalRepository(DbContext context) : base(context) { }
	}

	internal class LeagueRepository : GenericRepository<League>, ILeagueRepository
	{
		internal LeagueRepository(DbContext context) : base(context) { }
	}

	internal class LeagueTeamRepository : GenericRepository<LeagueTeam>, ILeagueTeamRepository
	{
		internal LeagueTeamRepository(DbContext context) : base(context) { }
	}

	internal class MatchRepository : GenericRepository<Match>, IMatchRepository
	{
		internal MatchRepository(DbContext context) : base(context) { }
	}

	internal class PlayerRepository : GenericRepository<Player>, IPlayerRepository
	{
		internal PlayerRepository(DbContext context) : base(context) { }
	}

	internal class PostRepository : GenericRepository<Post>, IPostRepository
	{
		internal PostRepository(DbContext context) : base(context) { }
	}

	internal class TeamRepository : GenericRepository<Team>, ITeamRepository
	{
		internal TeamRepository(DbContext context) : base(context) { }
	}

	internal class TourneyRepository : GenericRepository<Tourney>, ITourneyRepository
	{
		internal TourneyRepository(DbContext context) : base(context) { }
	}

	internal class TourneyTeamRepository : GenericRepository<TourneyTeam>, ITourneyTeamRepository
	{
		internal TourneyTeamRepository(DbContext context) : base(context) { }
	}

	internal class FileRepository : GenericRepository<File>, IFileRepository
	{
		internal FileRepository(DbContext context) : base(context) { }
	}

    internal class RegistrationRepository : GenericRepository<Registration>, IRegistrationRepository
    {
        internal RegistrationRepository(DbContext context) : base(context) { }
    }
}
