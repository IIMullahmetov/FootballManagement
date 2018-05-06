using FootballManagementApi.DAL.Models;

namespace FootballManagementApi.DAL.Repositories
{
    public interface IUserRepository : IGenericRepository<User> { }

    public interface ICommentRepository : IGenericRepository<Comment> { }

    public interface IGoalRepository : IGenericRepository<Goal> { }
	
    public interface IMatchRepository : IGenericRepository<Match> { }

    public interface IPlayerRepository : IGenericRepository<Player> { }

    public interface IPostRepository : IGenericRepository<Post> { }

    public interface ITeamRepository : IGenericRepository<Team> { }

    public interface ITourneyRepository : IGenericRepository<Tourney> { }

    public interface ITourneyTeamRepository : IGenericRepository<TourneyTeam> { }

    public interface IFileRepository : IGenericRepository<File> { }

    public interface IRegistrationRepository : IGenericRepository<Registration> { }
}
