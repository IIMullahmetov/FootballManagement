using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web.Http;
using FootballManagementApi.BlogResponses;
using FootballManagementApi.DAL;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.DAL.Repositories;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.Resources;
using FootballManagementApi.Responses;
using FootballManagementApi.BlogRequests;

namespace FootballManagementApi.Controllers
{
	[RoutePrefix("blog")]
	public class BlogController : BaseController
	{
		public BlogController(IUnitOfWork unitOfWork) : base(unitOfWork)
		{
		}

		[HttpGet]
		[Route("get_list")]
		public async Task<IHttpActionResult> GetListAsync([FromUri]int page = 0, [FromUri]int size = 10)
		{
			SelectOptions<Blog> options = new SelectOptions<Blog>
			{
				OrderBy = p => p.OrderByDescending(t => t.Id),
				Take = size,
				Skip = page * size
			};
			options.Includes.Add(p => p.Dislikes);
			options.Includes.Add(p => p.Likes);
			IBlogRepository repo = UnitOfWork.GetBlogRepository();

			IEnumerable<Blog> list = await repo.SelectAllAsync(options: options);
			int resultsCount = await repo.CountAsync(b => true);

			Paging paging = new Paging(page: page, count: resultsCount, size: size);

			GetListResponse response = new GetListResponse(paging)
			{
				Items = list.Select(p => new GetListReponseItem
				{
					Id = p.Id,
					IsMain = Random.Next(0, 1) == 0,
					Image = p.Items.FirstOrDefault(i => i.Type == Enums.BlogItemType.Image).Guid,
					Intro = p.Items.FirstOrDefault(i => i.Type == Enums.BlogItemType.Text).Text,
					Title = p.Title,
					Likes = p.Likes.Count,
					Dislikes = p.Dislikes.Count,
					CreateDt = p.CreateDt.ToString(DateTimeFormat)
				})
			};
			return Ok(response);
		}

		[HttpGet]
		[Route("get/{id:int}")]
		public async Task<IHttpActionResult> GetAsync(int id)
		{
			Blog blog = await UnitOfWork.GetBlogRepository().SelectByIdAsync(id)
				?? throw new ActionCannotBeExecutedException(ExceptionMessages.PostNotFound);
			GetResponse response = new GetResponse
			{
				Id = blog.Id,
				Title = blog.Title,
				CreateDt = blog.CreateDt.ToString(DateTimeFormat),
				Items = blog.Items.Select(i => new GetResponseItem
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
		[Auth.Authorize(Enums.Role.Admin)]
		public async Task<IHttpActionResult> CreateAsync([FromBody]CreateRequest request)
		{
			if (request.Items.Any(i => i.Type == Enums.BlogItemType.Image && !i.Guid.HasValue))
			{
				throw new ActionCannotBeExecutedException("Invalid request body");
			}
			User user = await GetCurrentUserAsync() ?? throw new ActionForbiddenException();
			Blog post = new Blog
			{
				Title = request.Title,
				CreateDt = DateTimeOffset.Now,
				User = user,
				Items = request.Items?.Select(i => new BlogItem
				{
					Type = i.Type,
					Text = i.Text,
					Guid = i.Guid,
					Link = i.Link,
					LinkText = i.LinkText
				}).ToList()
			};
			UnitOfWork.GetBlogRepository().Insert(post);
			await UnitOfWork.SaveChangesAsync();
			return Ok(new CreateResponse
			{
				Id = post.Id
			});
		}
	}
}
