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
using FootballManagementApi.PlayerResponses;
using FootballManagementApi.Resources;

namespace FootballManagementApi.Controllers
{
    [RoutePrefix("player")]
    public class PlayerController : BaseController
    {
        public PlayerController(IUnitOfWork unitOfWork) : base(unitOfWork)
        {

        }

        [HttpGet]
        [Route("get/{id:int}")]
        public async Task<IHttpActionResult> GetAsync(int id)
        {
            Player player = await UnitOfWork.GetPlayerRepository().SelectByIdAsync(id) ?? throw new ActionCannotBeExecutedException(ExceptionMessages.PlayerNotFound);

            GetResponse response = new GetResponse
            {
                Id = player.Id,
                FirstName = player.FirstName,
                LastName = player.LastName,
                Team = player.Team.Name,
                Age = DateTime.Now.Year - player.BirthDt.Year,
                Image = player.Image
            };

            return Ok(response);
        }
    }
}
