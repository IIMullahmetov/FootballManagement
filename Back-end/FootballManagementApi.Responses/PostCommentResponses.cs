using FootballManagementApi.Responses;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace FootballManagementApi.PostCommentResponses
{
	public class AddResponse : ResponseBase
	{
		[JsonProperty("id")]
		public int Id { get; set; }
	}

	public class GetListResponse : PagingResponse
	{
		public GetListResponse(Paging paging) : base(paging) { }

		[JsonProperty("items")]
		public IEnumerable<GetListResponseItem> Items { get; set; }
	}

	public class GetListResponseItem
	{
		[JsonProperty("id")]
		public int Id { get; set; }

		[JsonProperty("comment")]
		public string Comment { get; set; }

		[JsonProperty("firstName")]
		public string FirstName { get; set; }

		[JsonProperty("userId")]
		public int UserId { get; set; }

		[JsonProperty("image", NullValueHandling = NullValueHandling.Ignore)]
		public Guid? Image { get; set; }
	}
}
