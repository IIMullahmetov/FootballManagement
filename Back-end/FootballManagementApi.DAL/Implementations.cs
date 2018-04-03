using FootballManagementApi.DAL.Models;
using FootballManagementApi.DAL.Repositories;

namespace FootballManagementApi.DAL.Implementations
{
	internal class UserRepository : GenericRepository<User>, IUserRepository
	{
		internal UserRepository(Context context) : base(context) { }
	}
}
