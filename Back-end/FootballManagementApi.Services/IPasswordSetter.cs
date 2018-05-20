using FootballManagementApi.DAL.Models;

namespace FootballManagementApi.Services
{
    public interface IPasswordSetter
    {
        void SetPassword(User user, string password, string confirm);
    }
}
