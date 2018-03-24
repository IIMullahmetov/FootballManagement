using Microsoft.Owin;
using Owin;
using System.Web.Http;

[assembly: OwinStartup(typeof(FootballManagementApi.Startup))]

namespace FootballManagementApi
{
	public class Startup
	{
		public void Configuration(IAppBuilder app)
		{
			// For more information on how to configure your application, visit https://go.microsoft.com/fwlink/?LinkID=316888
			HttpConfiguration configuration = new HttpConfiguration();
			configuration.MapHttpAttributeRoutes();
			SwaggerConfig.Register(configuration);
			app.UseWebApi(configuration);
		}
	}
}
