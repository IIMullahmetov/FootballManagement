using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web.Http;
using FootballManagementApi.DAL;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.DAL.Repositories;
using FootballManagementApi.DAL.Specifications.Posts;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.PostResponses;
using FootballManagementApi.Requests.PostRequests;
using FootballManagementApi.Responses;

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
		public async Task<IHttpActionResult> GetListAsync([FromUri]int page = 0, [FromUri]int size = 10, [FromUri]string searchString = null)
		{
			SelectOptions<Post> options = new SelectOptions<Post>
			{
				OrderBy = p => p.OrderBy(t => t.Id),
				Take = size,
				Skip = page * size
			};
			IPostRepository repo = UnitOfWork.GetPostRepository();

			ISpecification<Post> specification = (new TitleSpecification(searchString)
				.Or(new ContentSpecification(searchString)))
				.And(new StatusSpecification(Enums.PostStatus.Published));
			IEnumerable<Post> list = await repo.SelectAsync(options: options, specification: specification);
			int resultsCount = await repo.CountAsync(specification: specification);

			int pageCount = resultsCount / 10 + 1;

			Paging paging = new Paging(page: page, count: pageCount, size: size);

			GetListResponse response = new GetListResponse(paging)
			{
				Items = list.Select(p => new GetListReponseItem
				{
					Id = p.Id,
					Image = p.Image,
					Intro = p.Intro,
					Title = p.Title
				})
			};

			return Ok(response);
		}

		[HttpGet]
		[Route("get/{id:int}")]
		public async Task<IHttpActionResult> GetAsync(int id)
		{
			Post post = await UnitOfWork.GetPostRepository().SelectByIdAsync(id)
				?? throw new ActionCannotBeExecutedException(GlobalExceptionHandler.ExceptionMessages.PostNotFound);
			GetResponse response = new GetResponse
			{
				Id = post.Id,
				Image = post.Image,
				Intro = post.Intro,
				Title = post.Title,
				Items = post.Items.Select(i => new GetResponseItem
				{
					Id = i.Id,
					Guid = i.Guid,
					Link = i.Link,
					LinkText = i.LinkText,
					Text = i.Text,
					Type = i.Type
				})
			};
			return Ok(response);
		}

		[HttpPost]
		[Route("create")]
		public async Task<IHttpActionResult> CreateAsync([FromBody]CreateRequest request)
		{
			User user = await GetCurrentUserAsync() ?? throw new ActionForbiddenException();
			Post post = new Post
			{
				Title = request.Title,
				Intro = request.Intro,
				Image = request.Image,
				CreateDt = DateTimeOffset.Now,
				User = user,
				//TODO Возможно будет реализовано так, что сначала черновик потом публикация
				Status = Enums.PostStatus.Published,
				Items = request.Items?.Select(i => new PostItem
				{
					Type = i.Type,
					Text = i.Text,
					Guid = i.Guid,
					Link = i.Link,
					LinkText = i.LinkText
				}).ToList()
			};
			UnitOfWork.GetPostRepository().Insert(post);
			await UnitOfWork.SaveChangesAsync();
			return Ok(new CreateResponse
			{
				Id = post.Id
			});
		}

		[HttpPost] 
		[Route("edit")]
		public async Task<IHttpActionResult> EditAsync([FromBody]EditRequest request)
		{
			User user = await GetCurrentUserAsync() ?? throw new ActionForbiddenException();
			Post post = await UnitOfWork.GetPostRepository().SelectByIdAsync(request.Id)
				?? throw new ActionCannotBeExecutedException(GlobalExceptionHandler.ExceptionMessages.PostNotFound);
			post.Title = request.Title;
			post.Intro = request.Intro;
			post.Image = request.Image;
			post.Items = request.Items?.Select(i => new PostItem
			{
				Type = i.Type,
				Text = i.Text,
				Guid = i.Guid,
				Link = i.Link,
				LinkText = i.LinkText
			}).ToList();

			return Ok();
		}

		[HttpPost]
		[Route("delete")]
		public async Task<IHttpActionResult> DeleteAsync([FromBody]DeleteRequest request)
		{
			User user = await GetCurrentUserAsync() ?? throw new ActionForbiddenException();
			Post post = await UnitOfWork.GetPostRepository().SelectByIdAsync(request.Id)
				?? throw new ActionCannotBeExecutedException(GlobalExceptionHandler.ExceptionMessages.PostNotFound);
			post.Status = Enums.PostStatus.Removed;
			await UnitOfWork.SaveChangesAsync();
			return Ok();
		}
	}
}
