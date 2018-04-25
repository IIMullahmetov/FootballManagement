using System.IO;
using System.Threading;
using System.Threading.Tasks;
using System.Web.Http;
using FootballManagementApi.Auth;
using FootballManagementApi.AuthRequests;
using FootballManagementApi.AuthResponse;
using FootballManagementApi.DAL;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.Services;
using Google.Apis.Auth.OAuth2;
using Google.Apis.Util.Store;

namespace FootballManagementApi.Controllers
{
	[RoutePrefix("auth")]
	public class AuthController : BaseController
	{
        private ILoginService _loginService;

        public AuthController(IUnitOfWork unitOfWork, ILoginService loginService) : base(unitOfWork)
		{
            _loginService = loginService;
		}
		
        [HttpPost]
		[Route("login")]
		public async Task<IHttpActionResult> LoginAsync([FromBody]LoginRequest request)
		{
            (string accessToken, System.Guid guid) = await _loginService.LoginAsync(request.Email, request.Password);
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
        public async Task<IHttpActionResult> GoogleAsync()
        {
            return Ok();
        }
    }
}
