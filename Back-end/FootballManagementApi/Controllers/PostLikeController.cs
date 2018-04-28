using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using FootballManagementApi.DAL;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.Services;

namespace FootballManagementApi.Controllers
{
    [Auth.Authorize]
    public class PostLikeController : BaseController
    {
        private IPostServices _postServices;

        public PostLikeController(IUnitOfWork unitOfWork, IPostServices postServices) : base(unitOfWork)
        {
            _postServices = postServices;
        }

        [HttpPost]
        [Route("post/{id:int}/like")]
        public async Task<IHttpActionResult> LikeAsync(int id)
        {
            User user = await GetCurrentUserAsync() ?? throw new ActionForbiddenException();
            await _postServices.LikeAsync(user, id);
            await UnitOfWork.SaveChangesAsync();
            return Ok();
        }

        [HttpPost]
        [Route("post/{id:int}/dislike")]
        public async Task<IHttpActionResult> DislikeAsync(int id)
        {
            User user = await GetCurrentUserAsync() ?? throw new ActionForbiddenException();
            await _postServices.DislikeAsync(user, id);
            await UnitOfWork.SaveChangesAsync();
            return Ok();
        }
    }
}
