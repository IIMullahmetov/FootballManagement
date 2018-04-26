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
using FootballManagementApi.Services;

namespace FootballManagementApi.Controllers
{
    public class ProfileController : BaseController
    {
        private IProfileService _profileService;

        public ProfileController(IUnitOfWork unitOfWork, IProfileService profileService) : base(unitOfWork)
        {
            _profileService = profileService;
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
            await _profileService.EditAsync(user, request.FirstName, request.LastName, request.Gender);
            await UnitOfWork.SaveChangesAsync();
            return Ok();
        }

        [HttpPost]
        [Route("change_email")]
        public async Task<IHttpActionResult> ChangeEmailAsync([FromBody]ChangeEmailRequest request)
        {
            User user = await GetCurrentUserAsync() ?? throw new ActionForbiddenException();
            await _profileService.ChangeEmailAsync(user, request.Email);
            await UnitOfWork.SaveChangesAsync();
            return Ok();
        }

        [HttpPost]
        [Route("change_image")]
        public async Task<IHttpActionResult> ChangeImageAsync()
        {
            User user = await GetCurrentUserAsync() ?? throw new ActionForbiddenException();
            byte[] image = (await ReadAsMultipartAsync()).FirstOrDefault().Value;
            string g = "";

            var dict = g.GroupBy(c => c)

            await _profileService.ChangeImageAsync(user, image);
            await UnitOfWork.SaveChangesAsync();
            return Ok();
        }

        [HttpPost]
        [Route("change_password")]
        public async Task<IHttpActionResult> ChangePasswordAsync([FromBody]ChangePasswordRequest request)
        {
            User user = await GetCurrentUserAsync() ?? throw new ActionForbiddenException();

            return Ok();
        }
    }
}
