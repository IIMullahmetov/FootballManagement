using System.IO;
using System.Threading;
using System.Threading.Tasks;
using System.Web.Http;
using FootballManagementApi.Auth;
using FootballManagementApi.AuthRequests;
using FootballManagementApi.AuthResponse;
using FootballManagementApi.DAL;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.Enums;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.RegistrationRequests;
using FootballManagementApi.Resources;
using FootballManagementApi.Services;
using Google.Apis.Auth.OAuth2;
using Google.Apis.Util.Store;

namespace FootballManagementApi.Controllers
{
	[RoutePrefix("auth")]
	public class AuthController : BaseController
	{
        private ILoginService _loginService;
        private IRegistrationService _registrationService;

        public AuthController(IUnitOfWork unitOfWork, ILoginService loginService, IRegistrationService registrationService) : base(unitOfWork)
		{
            _loginService = loginService;
            _registrationService = registrationService;
        }
		
        [HttpPost]
		[Route("login")]
		public async Task<IHttpActionResult> LoginAsync([FromBody]LoginRequest request)
		{
            (string accessToken, System.Guid guid) = await _loginService.LoginAsync(LoginType.Email ,request.Email, request.Password);
            LoginResposne response = new LoginResposne
            {
                AccessToken = accessToken,
                RefreshToken = guid
            };
            await UnitOfWork.SaveChangesAsync();
            return Ok(response);
		}

        //TODO Доделать
        [HttpPost]
        [Route("refresh_token")]
        public async Task<IHttpActionResult> RefreshTokenAsync()
        {
            return Ok();
        }

        [HttpPost]
        [Route("google")]
        public async Task<IHttpActionResult> GoogleAsync([FromBody]GoogleRequest request)
        {
            User user;
            user = await UnitOfWork.GetUserRepository().SelectFirstOrDefaultAsync(r => r.Email == request.Email);
            if (user == null)
            {
                Registration registration = await _registrationService.RegisterAsync(RegistrationType.Google, request.Email, null, request.FirstName, request.LastName, request.BirthDay, request.Gender);
                user = registration.User;
            }
            else if (user.Registration.Type.Equals(RegistrationType.Email))
                throw new ActionCannotBeExecutedException(ExceptionMessages.WrongRegistrationType);    

            if (!user.GoogleToken.Equals(request.GoogleToken))
                user.GoogleToken = request.GoogleToken;

            (string accessToken, System.Guid guid) = await _loginService.LoginAsync(LoginType.Google, request.Email);
            LoginResposne response = new LoginResposne
            {
                AccessToken = accessToken,
                RefreshToken = guid
            };

            await UnitOfWork.SaveChangesAsync();
            return Ok(response);
        }
    }
}
