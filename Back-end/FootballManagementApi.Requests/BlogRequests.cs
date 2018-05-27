using FootballManagementApi.Enums;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using System;
using System.Collections.Generic;

namespace FootballManagementApi.BlogRequests
{
	public class CreateRequest
	{
		[JsonProperty("title")]
		public string Title { get; set; }

		[JsonProperty("items")]
		public IEnumerable<BlogRequestItem> Items { get; set; }
	}


	public class BlogRequestItem
	{
		[JsonProperty("type")]
		[JsonConverter(typeof(StringEnumConverter))]
		public BlogItemType Type { get; set; }

		[JsonProperty("guid")]
		public Guid? Guid { get; set; }

		[JsonProperty("link")]
		public string Link { get; set; }

		[JsonProperty("linkText")]
		public string LinkText { get; set; }

		[JsonProperty("text")]
		public string Text { get; set; }
	}

}
