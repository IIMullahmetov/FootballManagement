using System.Threading.Tasks;

namespace FootballManagementApi.Services
{
    public interface IEmailValidator
    {
        Task ValidateAsync(string email);

        Task ValidateToRegistrationAsync(string email);
    }
}
