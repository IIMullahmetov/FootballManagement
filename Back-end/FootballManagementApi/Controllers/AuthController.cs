using System.Threading.Tasks;
using System.Web.Http;
using FootballManagementApi.DAL;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;

namespace FootballManagementApi.Controllers
{
	[RoutePrefix("auth")]
	public class AuthController : BaseController
	{
		public AuthController(IUnitOfWork unitOfWork) : base(unitOfWork)
		{

		}
		
		[Route("login")]
		public async Task<IHttpActionResult> LoginAsync()
		{
			throw new ActionCannotBeExecutedException("");
		}
	}
}
