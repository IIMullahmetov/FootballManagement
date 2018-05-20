using System.Threading.Tasks;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.Enums;

namespace FootballManagementApi.Services
{
    public interface IProfileService
    {
        Task ChangeEmailAsync(User user, string email);

        void Edit(User user, string firstName, string lastName, Gender? gender);

        Task ChangeImageAsync(User user, int id);

        void ChangePassword(User user, string password, string confirm);
    }
}
