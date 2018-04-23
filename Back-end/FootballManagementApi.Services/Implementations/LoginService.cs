using System;
using System.Threading.Tasks;

namespace FootballManagementApi.Services.Implementations
{
    public class LoginService : ILoginService
    {
        public Task<(string accessToken, Guid guid)> LoginAsync(string email, string password) => throw new NotImplementedException();

        public Task<(string accessToken, Guid guid)> RefreshTokenAsync(Guid guid) => throw new NotImplementedException();
    }
}
