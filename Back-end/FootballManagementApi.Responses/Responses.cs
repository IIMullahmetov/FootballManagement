using Newtonsoft.Json;
using System.Net;
using System.Net.Http;
using System.Threading;
using System.Threading.Tasks;
using System.Web.Http;

namespace FootballManagementApi.Responses
{
	public class ResponseBase
	{
		[JsonProperty("message", NullValueHandling = NullValueHandling.Ignore)]
		public string Message { get; set; }
	}

	public class PagingResponse : ResponseBase
	{
		[JsonProperty("paging")]
		public Paging Paging { get; set; }
	}

	public class ErrorResponse : IHttpActionResult
	{
		private readonly HttpRequestMessage _request;
		private readonly string _reason;
		private readonly HttpStatusCode _statusCode;

		public ErrorResponse(HttpRequestMessage request, HttpStatusCode statusCode,string reason)
		{
			_request = request;
			_reason = reason;
			_statusCode = statusCode;
		}
		
		public Task<HttpResponseMessage> ExecuteAsync(CancellationToken cancellationToken)
		{
			HttpResponseMessage response = _request.CreateResponse(_statusCode, _reason);
			return Task.FromResult(response);
		}
	}

	public class Paging
	{
		public int Count { get; private set; }

		public int Page { get; private set; }

		public int Size { get; private set; }

		public Paging(int count, int page, int size)
		{
			Count = count;
			Page = page;
			Size = size;
		}
	}
}
