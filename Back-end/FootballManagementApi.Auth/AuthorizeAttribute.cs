using System.Threading;
using System.Threading.Tasks;
using System.Web.Http.Controllers;
using System.Web.Http.Filters;
using FootballManagementApi.Enums;
using System.Net.Http;
using Microsoft.Owin;

namespace FootballManagementApi.Auth
{
	public class AuthorizeAttribute : ActionFilterAttribute
    {
        private Role? _role;

        public AuthorizeAttribute() { }

        public AuthorizeAttribute(Role role)
        {
            _role = role;
        }
	
		public override async Task OnActionExecutingAsync(HttpActionContext actionContext, CancellationToken cancellationToken)
		{
			IOwinContext context = actionContext.Request.GetOwinContext();

			
        }
        
    }
}
