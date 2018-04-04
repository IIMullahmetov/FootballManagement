using System.Diagnostics;
using System.Threading.Tasks;
using System.Web.Http;
using FootballManagementApi.DAL;

namespace FootballManagementApi.Controllers
{
	[RoutePrefix("post")]
	public class PostController : BaseController
	{
		public PostController(IUnitOfWork unitOfWork) : base(unitOfWork)
		{
		}

		[HttpGet]
		[Route("get_list")]
		public async Task<IHttpActionResult> GetListAsync(int page = 0, int size = 10)
		{
			DAL.Repositories.IUserRepository repo = UnitOfWork.GetUserRepository();
			return Ok(repo.Context.Database.Connection.ConnectionString);
		}

		[HttpGet]
		[Route("get/{id:int}")]
		public async Task<IHttpActionResult> GetAsync(int id)
		{
			return Ok();
		}

		[HttpPost]
		[Route("create")]
		public async Task<IHttpActionResult> CreateAsync()
		{
			return Ok();
		}

		[HttpPost]
		[Route("edit")]
		public async Task<IHttpActionResult> EditAsync()
		{
			return Ok();
		}

		[HttpPost]
		[Route("delete")]
		public async Task DeleteAsync()
		{

		}
	}
}
