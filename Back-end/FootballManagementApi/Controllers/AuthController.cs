using System.Collections.Generic;
using System.Drawing.Imaging;
using System.Threading.Tasks;
using System.Web.Http;
using FootballManagementApi.AuthRequests;
using FootballManagementApi.AuthResponse;
using FootballManagementApi.DAL;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.Enums;
using FootballManagementApi.FileStorage;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.Helpers;
using FootballManagementApi.RegistrationRequests;
using FootballManagementApi.Resources;
using FootballManagementApi.Services;
using Svg;

namespace FootballManagementApi.Controllers
{
	[RoutePrefix("auth")]
	public class AuthController : BaseController
	{
		private ILoginService _loginService;
		private IRegistrationService _registrationService;
		private IFileManager _fileManager;

		public AuthController(IUnitOfWork unitOfWork, ILoginService loginService, IRegistrationService registrationService, IFileManager fileManager) : base(unitOfWork)
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
			(string accessToken, System.Guid guid, User user) = await _loginService.LoginAsync(LoginType.Email, request.Email, request.Password);
			LoginResposne response = new LoginResposne
			{
				AccessToken = accessToken,
				RefreshToken = guid,
				Role = user.Role
			};
			await UnitOfWork.SaveChangesAsync();
			return Ok(response);
		}

		[HttpPost]
		[Route("sign_out")]
		public Task<IHttpActionResult> SignOutAsync()
		{
			return Task.FromResult(Ok());
		}

		/// <summary>
		/// Обновление токена пользователя
		/// </summary>
		/// <param name="request">текущий токен пользователя</param>
		/// <returns></returns>
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

			if (!user.GoogleToken.Equals(request.GoogleToken))
				user.GoogleToken = request.GoogleToken;

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

		[HttpPost]
		[Route("dodo")]
		public async Task DodOAsync()
		{
			IEnumerable<File> files = await UnitOfWork.GetFileRepository().SelectAsync(f => f.Name.EndsWith(".svg"));

			foreach (File file in files)
			{
				try
				{
					string path = PathHelper.GeneratePath(file.Guid);
					SvgDocument svg = SvgDocument.Open(path);
					System.Drawing.Bitmap res = svg.Draw();
					res.Save(path, ImageFormat.Png);
					file.Name = file.Name.Replace(".svg", ".png");
					await UnitOfWork.SaveChangesAsync();
				}
				catch
				{

				}
			}
		}
	}
}
