using System.Threading.Tasks;
using System.Web.Http;
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
            return Ok();
		}

        [HttpPost]
        [Route("refresh_token")]
        public async Task<IHttpActionResult> RefreshTokenAsync()
        {
            return Ok();
        }
    }
}
