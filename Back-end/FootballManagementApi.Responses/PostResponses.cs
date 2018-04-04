using System;
using FootballManagementApi.Responses;
using Newtonsoft.Json;

namespace FootballManagementApi.PostResponses
{
	public class GetListResponse : PagingResponse
	{
		public GetListResponse(Paging paging) : base(paging) { }

		[JsonProperty("id")]
		public int Id { get; set; }

		[JsonProperty("title")]
		public string Title { get; set; }

		[JsonProperty("publishDt")]
		public DateTimeOffset PublishDt { get; set; }
	}
}
