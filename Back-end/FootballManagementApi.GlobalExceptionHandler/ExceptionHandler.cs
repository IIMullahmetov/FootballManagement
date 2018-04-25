using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.Responses;
using System.Net;
using System.Threading;
using System.Threading.Tasks;
using System.Web.Http.ExceptionHandling;

namespace FootballManagementApi.GlobalExceptionHandler
{
	public class ExceptionHandler : System.Web.Http.ExceptionHandling.ExceptionHandler
	{
		public override Task HandleAsync(ExceptionHandlerContext context, CancellationToken cancellationToken)
		{
			if (context.Exception is ActionCannotBeExecutedException)
			{
				ActionCannotBeExecutedException exception = context.Exception as ActionCannotBeExecutedException;
				context.Result = new ErrorResponse(request: context.Request, statusCode: HttpStatusCode.BadRequest, reason: exception.Message);
				return context.Result.ExecuteAsync(cancellationToken);
			}

			if (context.Exception is ActionForbiddenException)
			{
				ActionCannotBeExecutedException exception = context.Exception as ActionCannotBeExecutedException;
				context.Result = new ErrorResponse(request: context.Request, statusCode: HttpStatusCode.Forbidden, reason: exception?.Message);
				return context.Result.ExecuteAsync(cancellationToken);
			}

			context.Result = new ErrorResponse(request: context.Request, statusCode: HttpStatusCode.InternalServerError, reason: context.Exception.Message);
			return context.Result.ExecuteAsync(cancellationToken);
		}
	}
}
