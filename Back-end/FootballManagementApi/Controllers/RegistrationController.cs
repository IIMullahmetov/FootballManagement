using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using FootballManagementApi.DAL;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.RegistrationRequests;
using FootballManagementApi.Services;

namespace FootballManagementApi.Controllers
{
    [RoutePrefix("registration")]
    public class RegistrationController : BaseController
    {
        private IRegistrationService _registrationService;

        public RegistrationController(IUnitOfWork unitOfWork, IRegistrationService registrationService) : base(unitOfWork)
        {
            _registrationService = registrationService;
        }

        [HttpPost]
        [Route("register")]
        public async Task<IHttpActionResult> RegisterAsync([FromBody]RegisterRequest request)
        {
            Registration registration = await _registrationService.RegisterAsync(registrationType: Enums.RegistrationType.Email, email: request.Email, password: request.Password, confirm: request.ConfirmPassword, firstName: request.FirstName, lastName: request.LastName, birthDt: request.BirthDay, gender: request.Gender);
            UnitOfWork.GetRegistrationRepository().Insert(registration);

            await UnitOfWork.SaveChangesAsync();
            return Ok();
        }

		public async Task<IHttpActionResult> RegisterAdminAsync([FromBody]RegisterRequest request)
		{
			Registration registration = await _registrationService.RegisterAsync(registrationType: Enums.RegistrationType.Email, email: request.Email, password: request.Password, confirm: request.ConfirmPassword, firstName: request.FirstName, lastName: request.LastName, birthDt: request.BirthDay, gender: request.Gender, role: Enums.Role.Admin);
			UnitOfWork.GetRegistrationRepository().Insert(registration);
			await UnitOfWork.SaveChangesAsync();
			return Ok();
		}

		//TODO Change to post
        [HttpGet]
        [Route("confirm")]
        public async Task<IHttpActionResult> ConfirmAsync([FromUri]Guid guid)
        {
            User user = await _registrationService.ConfirmAsync(guid);
            await UnitOfWork.SaveChangesAsync();
            return Ok();
        }
    }
}
