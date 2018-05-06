using FootballManagementApi.DAL.Models;
using FootballManagementApi.Enums;
using System;
using System.Threading.Tasks;

namespace FootballManagementApi.Services
{
    public interface ILoginService 
    {
        Task<(string accessToken, Guid guid, User user)> LoginAsync(LoginType loginType, string email, string password = null);

        Task<(string accessToken, Guid guid)> RefreshTokenAsync(Guid guid);
    }
}
