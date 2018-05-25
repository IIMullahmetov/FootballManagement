﻿using System;
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
			if (!actionContext.ModelState.IsValid || actionContext.ActionArguments.Any(kv => kv.Value == null))
			{
				actionContext.Response = new HttpResponseMessage
				{
					StatusCode = HttpStatusCode.BadRequest
				};
			}
		}
	}
}