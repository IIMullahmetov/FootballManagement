using FootballManagementApi.Enums;
using System;
using System.Threading.Tasks;

namespace FootballManagementApi.Services
{
    public interface ILoginService 
    {
        Task<(string accessToken, Guid guid)> LoginAsync(string email, string password);

        Task<(string accessToken, Guid guid)> LoginAsync(LoginType loginType, string email, string password);

        Task<(string accessToken, Guid guid)> RefreshTokenAsync(Guid guid);
    }
}
