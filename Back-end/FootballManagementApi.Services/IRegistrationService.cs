using System;
using System.Threading.Tasks;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.Enums;

namespace FootballManagementApi.Services
{
    public interface IRegistrationService
    {
        Task<Registration> RegisterAsync(RegistrationType registrationType, string email = null, string password = null, string confirm = null, string firstName = null,
            string lastName = null, DateTime? birthDt = null, Gender? gender = null, Role role = Role.User, string googleToken = null);

        Task<User> ConfirmAsync(Guid guid);
    }
}
