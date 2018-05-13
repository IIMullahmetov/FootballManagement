using FootballManagementApi.Auth;
using FootballManagementApi.DAL;
using FootballManagementApi.DAL.Repositories;
using FootballManagementApi.FileStorage;
using FootballManagementApi.MailSender;
using FootballManagementApi.Services;
using FootballManagementApi.Services.Implementations;
using Microsoft.Owin;
using Microsoft.Owin.Cors;
using Microsoft.Owin.Security.Google;
using Owin;
using SimpleInjector;
using SimpleInjector.Integration.WebApi;
using SimpleInjector.Lifestyles;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Threading;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.Http.ExceptionHandling;

[assembly: OwinStartup(typeof(FootballManagementApi.Startup))]

namespace FootballManagementApi
{
	public class Startup
	{
		private static Container Container { get; set; }

		public void Configuration(IAppBuilder app)
		{
			Container = new Container();
			Container.Options.DefaultScopedLifestyle = new AsyncScopedLifestyle();
			// For more information on how to configure your application, visit https://go.microsoft.com/fwlink/?LinkID=316888
			RegisterInstances();
			HttpConfiguration configuration = new HttpConfiguration();
			configuration.Services.Replace(typeof(IExceptionHandler), new GlobalExceptionHandler.ExceptionHandler());
			
			configuration.MapHttpAttributeRoutes();
			configuration.Filters.Add(new RequestFilterAttribute());
			SwaggerConfig.Register(configuration);
			Container.RegisterWebApiControllers(configuration);
			configuration.DependencyResolver = new SimpleInjectorWebApiDependencyResolver(Container);
			
			app.Use<AuthMiddleware>(new AuthManager(new AuthOption()));
			Task.Factory.StartNew(MatchStatusChanger);
			app.UseCors(CorsOptions.AllowAll);
			app.UseWebApi(configuration);
		}

		private async void MatchStatusChanger()
		{
			while (true)
			{
				using (IUnitOfWork unitOfWork = new UnitOfWork<Context>())
				{
					IMatchRepository repo = unitOfWork.GetMatchRepository();
					IEnumerable<DAL.Models.Match> matches = await repo.SelectAsync(m => m.Status == Enums.MatchStatus.Pending);

					foreach(DAL.Models.Match match in matches)
					{
						if (match.StartDt >= DateTimeOffset.Now)
						{
							match.Status = Enums.MatchStatus.Started;
						}
					}

					matches = await repo.SelectAsync(m => m.Status == Enums.MatchStatus.Started);
					foreach(DAL.Models.Match match in matches)
					{
						if (match.EndDt <= DateTimeOffset.Now)
						{
							match.Status = Enums.MatchStatus.Finished;
						}
					}

					await unitOfWork.SaveChangesAsync();

					Thread.Sleep(60000);
				}
			}
		}

		private void RegisterInstances()
		{
			Container.Register<IUnitOfWork, UnitOfWork<Context>>(lifestyle: Lifestyle.Scoped);
			Container.Register<IFileManager, FileManager>(lifestyle: Lifestyle.Scoped);
			Container.Register<IRegistrationService, RegistrationService>(lifestyle: Lifestyle.Scoped);
			Container.Register<IEmailValidator, EmailValidator>(lifestyle: Lifestyle.Scoped);
			Container.Register<IMailSender, MailSender.MailSender>(lifestyle: Lifestyle.Scoped);
			Container.Register<IAuthOption, AuthOption>(lifestyle: Lifestyle.Singleton);
			Container.Register<ILoginService, LoginService>(lifestyle: Lifestyle.Scoped);
			Container.Register<IPasswordSetter, PasswordSetter>(lifestyle: Lifestyle.Scoped);
			Container.Register<IProfileService, ProfileService>(lifestyle: Lifestyle.Scoped);
			Container.Register<IPostServices, PostServices>(lifestyle: Lifestyle.Scoped);

		}
	}
}
