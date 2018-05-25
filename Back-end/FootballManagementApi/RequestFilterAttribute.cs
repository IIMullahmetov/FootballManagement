using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http.Controllers;
using System.Web.Http.Filters;
using System.Web.Http.Results;

namespace FootballManagementApi
{
	public class RequestFilterAttribute : ActionFilterAttribute
	{
		
		public override async Task OnActionExecutingAsync(HttpActionContext actionContext, CancellationToken cancellationToken)
		{
			if (!actionContext.ModelState.IsValid || (actionContext.Request.Method.Method == "POST" && actionContext.ActionArguments.Any(k => k.Value == null) && actionContext.ActionArguments.Count > 0))
			{
				//if (!actionContext.ModelState.IsValid)
				//{
				//	actionContext.Response = new HttpResponseMessage(HttpStatusCode.BadGateway)
				//	{
				//		Content = new StringContent(actionContext.ModelState.E)
				//	};
				//}
				actionContext.Response = new HttpResponseMessage(HttpStatusCode.BadGateway)
				{
					Content = new StringContent("Invalid fucking request body or headers")
				};
			}
		}
	}
}