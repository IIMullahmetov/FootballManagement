using System.Threading;
using System.Threading.Tasks;
using System.Web.Http.Controllers;
using System.Web.Http.Filters;
using FootballManagementApi.Enums;
using System.Net.Http;
using Microsoft.Owin;
using System;

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

            Principal principal = context.Request.User as Principal;

            if (principal == null)
            {
                actionContext.Response = new HttpResponseMessage
                {
                    StatusCode = System.Net.HttpStatusCode.Unauthorized,
                };
            }
			else
			{
				if (principal.ExpireAt < DateTimeOffset.Now)
				{
					actionContext.Response = new HttpResponseMessage
					{
						StatusCode = System.Net.HttpStatusCode.Unauthorized,
					};
				}
			}
        }
    }
}
