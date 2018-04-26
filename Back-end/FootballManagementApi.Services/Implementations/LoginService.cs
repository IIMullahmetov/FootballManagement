using System;
using System.Linq;
using System.Threading.Tasks;
using FootballManagementApi.Auth;
using FootballManagementApi.DAL;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.Enums;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.Helpers;

namespace FootballManagementApi.Services.Implementations
{
    public class LoginService : ILoginService
    {
        private IEmailValidator _emailValidator;
        private IUnitOfWork _unitOfWork;
        private IAuthOption _authOption;

        public LoginService(IEmailValidator emailValidator, IUnitOfWork unitOfWork, IAuthOption authOption)
        {
            _emailValidator = emailValidator;
            _unitOfWork = unitOfWork;
            _authOption = authOption;
        }

        public async Task<(string accessToken, Guid guid)> LoginAsync(LoginType loginType, string email, string password = null)
        {
            ValidateData(email, password);
            User user = await _unitOfWork.GetUserRepository().SelectFirstOrDefaultAsync(u => u.Email == email && u.Status == UserStatus.Active) ?? throw new ActionForbiddenException();
            
            if (loginType == LoginType.Google)
            {
                string jwt = GetJwt(user, LoginType.Google);
                RefreshToken refreshToken = new RefreshToken
                {
                    ExpireAt = DateTimeOffset.Now.AddSeconds(_authOption.RefreshTokenLife),
                    CreateDt = DateTimeOffset.Now,
                    User = user,
                    Guid = Guid.NewGuid()
                };
                return (jwt, refreshToken.Guid);
            }

            if (loginType == LoginType.Email)
            {
                byte[] pass = PasswordHelper.HashPassword(password, user.Salt);

                if (user.Password.SequenceEqual(pass))
                {
                    string jwt = GetJwt(user, LoginType.Email);
                    RefreshToken refreshToken = new RefreshToken
                    {
                        ExpireAt = DateTimeOffset.Now.AddSeconds(_authOption.RefreshTokenLife),
                        CreateDt = DateTimeOffset.Now,
                        User = user,
                        Guid = Guid.NewGuid()
                    };
                    return (jwt, refreshToken.Guid);
                }
            }

            throw new ActionForbiddenException();
        }

        public Task<(string accessToken, Guid guid)> RefreshTokenAsync(Guid guid) => throw new NotImplementedException();

        //TODO Implement
        private void ValidateData(string email, string password = null)
        {

        }

        private string GetJwt(User user, LoginType loginType)
        {
            Jwt jwt = new Jwt
            {
                Email = user.Email,
                ExpireAt = _authOption.TokenLife,
                LoginType = loginType,
                Id = user.Id,
                Role = user.Role
            };

            return JsonWebToken.Encode(jwt, _authOption.Secret, JwtHashAlgorithm.RS256);
        }
    }
}
