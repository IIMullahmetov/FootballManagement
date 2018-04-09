using FootballManagementApi.Enums;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using System;
using System.Collections.Generic;

namespace FootballManagementApi.Requests.PostRequests
{
	public class CreateRequest
	{
		[JsonProperty("title")]
		public string Title { get; set; }

		[JsonProperty("intro")]
		public string Intro { get; set; }

		[JsonProperty("image")]
		public Guid? Image { get; set; }

		[JsonProperty("items")]
		public IEnumerable<PostRequestItem> Items { get; set; }
	}

	public class EditRequest
	{
		[JsonProperty("id")]
		public int Id { get; set; }

		[JsonProperty("title")]
		public string Title { get; set; }

		[JsonProperty("intro")]
		public string Intro { get; set; }

		[JsonProperty("image")]
		public Guid? Image { get; set; }

		[JsonProperty("items")]
		public IEnumerable<PostRequestItem> Items { get; set; }
	}

	public class PostRequestItem
	{
		[JsonProperty("type")]
		[JsonConverter(typeof(StringEnumConverter))]
		public PostItemType Type { get; set; }

		[JsonProperty("guid")]
		public Guid? Guid { get; set; }

		[JsonProperty("link")]
		public string Link { get; set; }

		[JsonProperty("linkText")]
		public string LinkText { get; set; }

		[JsonProperty("text")]
		public string Text { get; set; }
	}

	public class DeleteRequest
	{
		[JsonProperty("id")]
		public int Id { get; set; }
	}
}
