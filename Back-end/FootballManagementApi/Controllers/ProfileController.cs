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
using FootballManagementApi.ProfileRequests;
using FootballManagementApi.ProfileResponses;

namespace FootballManagementApi.Controllers
{
    public class ProfileController : BaseController
    {
        public ProfileController(IUnitOfWork unitOfWork) : base(unitOfWork)
        {
        }

        [HttpGet]
        [Route("get")]
        public async Task<IHttpActionResult> GetAsync()
        {
            User user = await GetCurrentUserAsync() ?? throw new ActionForbiddenException();
            GetResponse response = new GetResponse
            {
                Email = user.Email,
                FirstName = user.FirstName,
                Image = user.Image,
                LastName = user.LastName,
                Gender = user.Gender
            };
            return Ok(response);
        }

        [HttpPost]
        [Route("edit")]
        public async Task<IHttpActionResult> EditAsync([FromBody]EditRequest request)
        {
            User user = await GetCurrentUserAsync() ?? throw new ActionForbiddenException();

            return Ok();
        }

        [HttpPost]
        [Route("change_email")]
        public async Task<IHttpActionResult> ChangeEmailAsync()
        {
            return Ok();
        }

        [HttpPost]
        [Route("change_image")]
        public async Task<IHttpActionResult> ChangeImageAsync()
        {
            return Ok();
        }
    }
}
