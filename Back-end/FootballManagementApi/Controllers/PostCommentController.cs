﻿using System.Collections.Generic;
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
using Swashbuckle.Swagger.Annotations;

namespace FootballManagementApi.Controllers
{
	public class PostCommentController : BaseController
	{
		public PostCommentController(IUnitOfWork unitOfWork) : base(unitOfWork)
		{
		}
        /// <summary>
        /// получение списка комментариев поста
        /// </summary>
        /// <param name="id">идентификатор поста</param>
        /// <param name="page">номер странцы</param>
        /// <param name="size">размер страницы</param>
        /// <returns></returns>
		[HttpGet]
		[Route("post/{id}/comment/get_list")]
		[SwaggerResponse(System.Net.HttpStatusCode.OK, Type = typeof(GetListResponse))]
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
			ISpecification<Comment> specification = new StatusSpecification(Enums.CommentStatus.Active)
				.And(new PostSpecification(id));
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

        /// <summary>
        /// Добавление комментарив к посту
        /// </summary>
        /// <param name="id">идентификатор поста</param>
        /// <param name="request">данные о комментарии</param>
        /// <returns></returns>
		[HttpPost]
		[Route("post/{id}/comment/add")]
		[SwaggerResponse(System.Net.HttpStatusCode.OK, Type = typeof(AddResponse))]
		[Auth.Authorize]
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

			return Ok(new AddResponse
			{
				Id = comment.Id,
				UserId = comment.UserId,
				Comment = comment.Body,
				FirstName = user.FirstName,
				Image = comment.User.Image
			});
		}
        /// <summary>
        /// Удаление комментариев к посту
        /// </summary>
        /// <param name="id">идентификатор поста</param>
        /// <param name="request">данные о комментарии</param>
        /// <returns></returns>
		[HttpPost]
		[Route("post/{id}/comment/delete")]
		[Auth.Authorize]
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
