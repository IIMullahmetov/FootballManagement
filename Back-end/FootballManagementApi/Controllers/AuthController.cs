using System.Threading.Tasks;
using System.Web.Http;
using FootballManagementApi.Auth;
using FootballManagementApi.AuthRequests;
using FootballManagementApi.DAL;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.Services;

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
		public async Task<IHttpActionResult> LoginAsync()
		{
            IAuthOption option = new AuthOption();
            Jwt jwt = new Jwt
            {
                Email = "imullahmetov@gmail.com",
                Id = 1,
                ExpireAt = 100,
                Role = Enums.Role.User,
                LoginType = Enums.LoginType.Email
            };
            string t = JsonWebToken.Encode(jwt, option.Secret, JwtHashAlgorithm.RS256);
            return Ok(t);
		}

        [HttpPost]
        [Route("refresh_token")]
        public async Task<IHttpActionResult> RefreshTokenAsync()
        {
            return Ok();
        }
    }
}
