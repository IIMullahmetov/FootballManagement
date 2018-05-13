using FootballManagementApi.Enums;
using FootballManagementApi.Responses;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
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
		public IEnumerable<GetItem> Items { get; set; }
	}

	public class GetItem
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

		[JsonProperty("statud")]
		[JsonConverter(typeof(StringEnumConverter))]
		public TourneyTeamStatus Status { get; set; }
	}
}
