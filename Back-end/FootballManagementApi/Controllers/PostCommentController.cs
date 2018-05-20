using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web.Http;
using FootballManagementApi.DAL;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.DAL.Repositories;
using FootballManagementApi.DAL.Specifications.Comments;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.PostCommentResponses;
using FootballManagementApi.Requests.PostCommentRequests;
using FootballManagementApi.Resources;
using FootballManagementApi.Responses;

namespace FootballManagementApi.Controllers
{
	public class PostCommentController : BaseController
	{
		public PostCommentController(IUnitOfWork unitOfWork) : base(unitOfWork)
		{
		}

		[HttpGet]
		[Route("post/{id}/comment/get_list")]
		public async Task<IHttpActionResult> GetListAsync([FromUri]int id, [FromUri]int page = 0, [FromUri]int size = 10)
		{
			Post post = await UnitOfWork.GetPostRepository().SelectFirstOrDefaultAsync(p => p.Id == id && p.Status == Enums.PostStatus.Published)
				?? throw new ActionCannotBeExecutedException(ExceptionMessages.PostNotFound);
			SelectOptions<Comment> options = new SelectOptions<Comment>
			{
				OrderBy = p => p.OrderByDescending(t => t.Id),
				Take = size,
				Skip = page * size
			};
			StatusSpecification specification = new StatusSpecification(Enums.CommentStatus.Active);
			ICommentRepository repo = UnitOfWork.GetCommentRepository();
			IEnumerable<Comment> list = await repo.SelectAsync(specification, options);
			int resultsCount = await repo.CountAsync(specification);

			Paging paging = new Paging(page: page, count: resultsCount, size: size);

			GetListResponse response = new GetListResponse(paging)
			{
				Items = list.Select(c => new GetListResponseItem
				{
					Id = c.Id,
					UserId = c.UserId,
					Comment = c.Body,
					FirstName = c.User.FirstName,
					Image = c.User.Image
				})
			};

			return Ok(response);
		}


		[HttpPost]
		[Route("post/{id}/comment/add")]
		public async Task<IHttpActionResult> AddAsync([FromUri]int id, [FromBody]AddRequest request)
		{
			User user = await GetCurrentUserAsync() ?? throw new ActionForbiddenException();
			Post post = await UnitOfWork.GetPostRepository().SelectFirstOrDefaultAsync(p => p.Id == id && p.Status == Enums.PostStatus.Published)
				?? throw new ActionCannotBeExecutedException(ExceptionMessages.PostNotFound);

			Comment comment = new Comment
			{
				User = user,
				Body = request.Comment,
				Status = Enums.CommentStatus.Active
			};

			post.Comments.Add(comment);

			await UnitOfWork.SaveChangesAsync();

			return Ok(new AddResponse { Id = comment.Id });
		}

		[HttpPost]
		[Route("post/{id}/comment/delete")]
		public async Task<IHttpActionResult> DeleteAsync([FromUri]int id, [FromBody]DeleteRequest request)
		{
			User user = await GetCurrentUserAsync() ?? throw new ActionForbiddenException();

			Post post = await UnitOfWork.GetPostRepository().SelectFirstOrDefaultAsync(p => p.Id == id && p.Status == Enums.PostStatus.Published)
				?? throw new ActionCannotBeExecutedException(ExceptionMessages.PostNotFound);

			Comment comment = post.Comments.FirstOrDefault(c => c.Id == request.Id)
				?? throw new ActionCannotBeExecutedException(ExceptionMessages.CommentNotFound);

			comment.Status = Enums.CommentStatus.Removed;
			await UnitOfWork.SaveChangesAsync();
			return Ok();
		}
	}
}
