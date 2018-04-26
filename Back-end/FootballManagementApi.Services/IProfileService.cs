using System.Threading.Tasks;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.Enums;

namespace FootballManagementApi.Services
{
    public interface IProfileService
    {
        Task ChangeEmailAsync(User user, string email);

        Task EditAsync(User user, string firstName, string lastName, Gender? gender);

        Task ChangeImageAsync(User user, byte[] image);
    }
}
