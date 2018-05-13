using System;
using System.Linq;
using System.Net.Mail;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using FootballManagementApi.Auth;
using FootballManagementApi.DAL;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.Enums;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.Helpers;
using FootballManagementApi.Resources;

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

        public async Task<(string accessToken, Guid guid, User user)> LoginAsync(LoginType loginType, string email, string password = null)
        {
            ValidateData(email, password);
            User user = await _unitOfWork.GetUserRepository().SelectFirstOrDefaultAsync(u => u.Email == email && u.Status == UserStatus.Active) ?? throw new ActionForbiddenException();
            
            if (loginType == LoginType.Google)
            {
                string jwt = GetJwt(user, LoginType.Google);
                RefreshToken refreshToken = new RefreshToken
                {
                    ExpireAt = DateTimeOffset.Now.AddSeconds(_authOption.TokenLife),
                    CreateDt = DateTimeOffset.Now,
                    User = user,
                    Guid = Guid.NewGuid()
                };
				user.RefreshTokens.Add(refreshToken);
                return (jwt, refreshToken.Guid, user);
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
					user.RefreshTokens.Add(refreshToken);
					return (jwt, refreshToken.Guid, user);
                }
            }

            throw new ActionForbiddenException();
        }

        public async Task<(string accessToken, Guid guid)> RefreshTokenAsync(Guid guid, User user)
		{
			RefreshToken refreshToken = user.RefreshTokens.FirstOrDefault(t => t.Guid == guid && t.ExpireAt >= DateTimeOffset.Now)
				?? throw new ActionCannotBeExecutedException(ExceptionMessages.TokenHasExpired);

			string token = GetJwt(user, (LoginType)user.Registration.Type);
			RefreshToken newRefreshToken = new RefreshToken
			{
				User = user,
				ExpireAt = DateTimeOffset.Now.AddSeconds(_authOption.RefreshTokenLife),
				CreateDt = DateTimeOffset.Now,
				Guid = Guid.NewGuid()
			};
			user.RefreshTokens.Add(newRefreshToken);
			return (token, newRefreshToken.Guid);
		}

        private void ValidateData(string email, string password = null)
        {
            try
            {
                MailAddress mailAddress = new MailAddress(email);
            }
            catch(Exception ex)
            {
                throw new ActionCannotBeExecutedException(ExceptionMessages.InvalidEmailFormat);
            }

            if(password != null)
            {
                var hasNumber = new Regex(@"[0-9]+");
                var hasUpperChar = new Regex(@"[A-Z]+");
                var has8Char = new Regex(@".{8,}");

                var isValidated = hasNumber.IsMatch(password) && 
                    hasUpperChar.IsMatch(password) &&
                    has8Char.IsMatch(password);

                if (!isValidated)
                    throw new ActionCannotBeExecutedException(ExceptionMessages.InvalidPassword);
            }
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
