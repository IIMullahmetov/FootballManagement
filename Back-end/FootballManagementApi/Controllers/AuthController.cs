﻿using System.IO;
using System.Threading;
using System.Threading.Tasks;
using System.Web.Http;
using FootballManagementApi.Auth;
using FootballManagementApi.AuthRequests;
using FootballManagementApi.AuthResponse;
using FootballManagementApi.DAL;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.Enums;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.RegistrationRequests;
using FootballManagementApi.Resources;
using FootballManagementApi.Services;
using Google.Apis.Auth.OAuth2;
using Google.Apis.Util.Store;

namespace FootballManagementApi.Controllers
{
	[RoutePrefix("auth")]
	public class AuthController : BaseController
	{
        private ILoginService _loginService;
        private IRegistrationService _registrationService;
       
        public AuthController(IUnitOfWork unitOfWork, ILoginService loginService, IRegistrationService registrationService) : base(unitOfWork)
		{
            _loginService = loginService;
            _registrationService = registrationService;
        }
		/// <summary>
        /// Авторизация пользователя
        /// </summary>
        /// <param name="request">Данные для авторизации пользователя</param>
        /// <returns></returns>
        [HttpPost]
		[Route("login")]
		public async Task<IHttpActionResult> LoginAsync([FromBody]LoginRequest request)
		{
            (string accessToken, System.Guid guid, User user) = await _loginService.LoginAsync(LoginType.Email ,request.Email, request.Password);
            LoginResposne response = new LoginResposne
            {
                AccessToken = accessToken,
                RefreshToken = guid,
				Role = user.Role
            };
            await UnitOfWork.SaveChangesAsync();
            return Ok(response);
		}

        /// <summary>
        /// Обновление токена пользователя
        /// </summary>
        /// <param name="request">текущий токен пользователя</param>
        /// <returns></returns>
        //TODO Доделать
        [HttpPost]
        [Route("refresh_token")]
        public async Task<IHttpActionResult> RefreshTokenAsync([FromBody]RefreshTokenRequest request)
        {
			User user = await GetCurrentUserAsync() ?? throw new ActionForbiddenException();
			(string accessToken, System.Guid guid) = await _loginService.RefreshTokenAsync(request.RefreshToken, user);
			await UnitOfWork.SaveChangesAsync();
            return Ok(new LoginResposne
			{
				AccessToken = accessToken,
				RefreshToken = guid,
				Role = user.Role
			});
        }
        /// <summary>
        /// Авторизация через google
        /// </summary>
        /// <param name="request">Данные пользователя для авторизации через google</param>
        /// <returns></returns>
        [HttpPost]
        [Route("google")]
        public async Task<IHttpActionResult> GoogleAsync([FromBody]GoogleRequest request)
        {
            User user;
            user = await UnitOfWork.GetUserRepository().SelectFirstOrDefaultAsync(r => r.Email == request.Email);
            if (user == null)
            {
                Registration registration = await _registrationService.RegisterAsync(registrationType: RegistrationType.Google, email: request.Email, firstName: request.FirstName, lastName: request.LastName, birthDt: request.BirthDay, gender: request.Gender);
                user = registration.User;
            }
            else if (user.Registration.Type.Equals(RegistrationType.Email))
                throw new ActionCannotBeExecutedException(ExceptionMessages.WrongRegistrationType);    

            if (user.GoogleToken == null || !user.GoogleToken.Equals(request.GoogleToken))
                user.GoogleToken = request.GoogleToken;

            await UnitOfWork.SaveChangesAsync();

            (string accessToken, System.Guid guid, User user) result = await _loginService.LoginAsync(LoginType.Google, request.Email);
            LoginResposne response = new LoginResposne
            {
                AccessToken = result.accessToken,
                RefreshToken = result.guid,
				Role = result.user.Role
			};

            await UnitOfWork.SaveChangesAsync();
            return Ok(response);
        }
    }
}
