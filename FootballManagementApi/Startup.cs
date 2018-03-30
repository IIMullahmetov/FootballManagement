using FootballManagementApi.DAL;
using Microsoft.Owin;
using Owin;
using SimpleInjector;
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
			// For more information on how to configure your application, visit https://go.microsoft.com/fwlink/?LinkID=316888
			RegisterInstances();
			HttpConfiguration configuration = new HttpConfiguration();
			configuration.Services.Replace(typeof(IExceptionHandler), new GlobalExceptionHandler.ExceptionHandler());
			configuration.MapHttpAttributeRoutes();
			SwaggerConfig.Register(configuration);
			
			app.UseWebApi(configuration);
		}

		private void RegisterInstances()
		{
			Container.Register<IUnitOfWorkFactory, UnitOfWorkFactory>(lifestyle: Lifestyle.Singleton);
			
		}
	}
}
