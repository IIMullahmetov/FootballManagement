using FootballManagementApi.Responses;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace FootballManagementApi.TourneyResponses
{
	public class GetListResponse : PagingResponse
	{
		public GetListResponse(Paging paging) : base(paging) { }

		[JsonProperty("items")]
		public IEnumerable<GetListItem> Items { get; set; }
	}

	public class GetListItem
	{
		[JsonProperty("id")]
		public int Id { get; set; }

		[JsonProperty("name")]
		public string Name { get; set; }

		[JsonProperty("startDt")]
		public DateTime StartDt { get; set; }

		[JsonProperty("endDt")]
		public DateTime EndDt { get; set; }
	}

	public class GetResponse : ResponseBase
	{
		[JsonProperty("id")]
		public int Id { get; set; }

		[JsonProperty("name")]
		public string Name { get; set; }

		[JsonProperty("startDt")]
		public DateTime StartDt { get; set; }

		[JsonProperty("endDt")]
		public DateTime EndDt { get; set; }

		[JsonProperty("items")]
		public IEnumerable<GetItems> Items { get; set; }
	}

	public class GetItems
	{
		[JsonProperty("id")]
		public int Id { get; set; }

		[JsonProperty("name")]
		public string Name { get; set; }

		[JsonProperty("image")]
		public Guid Image { get; set; }

		[JsonProperty("goals")]
		public int Goals { get; set; }

		[JsonProperty("position")]
		public int Position { get; set; }
	}
}
