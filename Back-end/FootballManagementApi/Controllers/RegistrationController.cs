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
            Registration registration = await _registrationService.RegisterAsync(Enums.RegistrationType.Email, request.Email, request.Password, request.FirstName, request.LastName, request.BirthDay, request.Gender);
            UnitOfWork.GetRegistrationRepository().Insert(registration);

            await UnitOfWork.SaveChangesAsync();
            return Ok();
        }

        [HttpPost]
        [Route("confirm")]
        public async Task<IHttpActionResult> ConfirmAsync([FromUri]Guid guid)
        {
            User user = await _registrationService.ConfirmAsync(guid);
            await UnitOfWork.SaveChangesAsync();
            return Ok();
        }
    }
}
