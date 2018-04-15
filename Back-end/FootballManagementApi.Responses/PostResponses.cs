using System;
using System.Collections.Generic;
using FootballManagementApi.Enums;
using FootballManagementApi.Responses;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace FootballManagementApi.PostResponses
{
	public class GetListResponse : PagingResponse
	{
		public GetListResponse(Paging paging) : base(paging) { }

		[JsonProperty("publishDt")]
		public DateTimeOffset PublishDt { get; set; }

		[JsonProperty("items")]
		public IEnumerable<GetListReponseItem> Items { get; set; }
	}

	public class GetListReponseItem
	{
		[JsonProperty("id")]
		public int Id { get; set; }

		[JsonProperty("title")]
		public string Title { get; set; }

		[JsonProperty("intro")]
		public string Intro { get; set; }

		[JsonProperty("image")]
		public Guid? Image { get; set; }
	}

	public class GetResponse
	{
		[JsonProperty("id")]
		public int Id { get; set; }

		[JsonProperty("title")]
		public string Title { get; set; }

		[JsonProperty("intro", NullValueHandling = NullValueHandling.Ignore)]
		public string Intro { get; set; }

		[JsonProperty("image", NullValueHandling = NullValueHandling.Ignore)]
		public Guid? Image { get; set; }

		[JsonProperty("items")]
		public IEnumerable<GetResponseItem> Items { get; set; }
	}

	public class GetResponseItem
	{
		[JsonProperty("id")]
		public int Id { get; set; }

		[JsonProperty("type")]
		[JsonConverter(typeof(StringEnumConverter))]
		public PostItemType Type { get; set; }

		[JsonProperty("guid", NullValueHandling = NullValueHandling.Ignore)]
		public Guid? Guid { get; set; }

		[JsonProperty("link", NullValueHandling = NullValueHandling.Ignore)]
		public string Link { get; set; }

		[JsonProperty("linkText", NullValueHandling = NullValueHandling.Ignore)]
		public string LinkText { get; set; }

		[JsonProperty("text", NullValueHandling = NullValueHandling.Ignore)]
		public string Text { get; set; }
	}

	public class CreateResponse : ResponseBase
	{
		[JsonProperty("id")]
		public int Id { get; set; }
	}
}
