using FootballManagementApi.Auth;
using FootballManagementApi.DAL;
using FootballManagementApi.FileStorage;
using FootballManagementApi.MailSender;
using FootballManagementApi.Services;
using FootballManagementApi.Services.Implementations;
using Microsoft.Owin;
using Microsoft.Owin.Security.Google;
using Owin;
using SimpleInjector;
using SimpleInjector.Integration.WebApi;
using SimpleInjector.Lifestyles;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
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
            SwaggerConfig.Register(configuration);
            Container.RegisterWebApiControllers(configuration);
            configuration.DependencyResolver = new SimpleInjectorWebApiDependencyResolver(Container);
            app.Use<AuthMiddleware>(new AuthManager(new AuthOption()));
            GoogleOAuth2AuthenticationOptions googleOptions = new GoogleOAuth2AuthenticationOptions()
            {
                ClientId = "760928189473-v5u1hpik6d5u42od28hqi8q70ndulnoe.apps.googleusercontent.com",
                ClientSecret = "H8BEZfY2ljCwPW8CPM7_g6h3"
            };
            //app.UseGoogleAuthentication(googleOptions);
            app.UseWebApi(configuration);
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
            
            //IEnumerable<Type> types = AppDomain.CurrentDomain.GetAssemblies().SelectMany(a => a.GetTypes());

            //foreach (Type intrfc in types.Where(t => t.IsInterface && t.IsPublic))
            //{
            //	Type clss = types
            //		.FirstOrDefault(t => t.IsClass && t.IsPublic && t.GetInterfaces()
            //		.Contains(intrfc) && !t.IsAbstract && types.Count(t1 => t1.GetInterfaces()
            //		.Contains(intrfc)) == 1);
            //	if (clss != null)
            //	{
            //		try
            //		{
            //			Container.Register(intrfc, clss, Lifestyle.Scoped);
            //		}
            //		catch { }
            //	}
            //}
        }
    }
}
