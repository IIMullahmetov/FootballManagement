using System;
using System.Collections.Generic;
using FootballManagementApi.Enums;
using FootballManagementApi.Responses;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace FootballManagementApi.MatchResponses
{
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

		[JsonProperty("startDt")]
		public DateTimeOffset StartDt { get; set; }

		[JsonProperty("status")]
		[JsonConverter(typeof(StringEnumConverter))]
		public MatchStatus Status { get; set; }
		
		[JsonProperty("home")]
		public GetListResponseItemTeam Home { get; set; }

		[JsonProperty("guest")]
		public GetListResponseItemTeam Guest { get; set; }
	}

	public class GetListResponseItemTeam
	{
		[JsonProperty("id")]
		public int Id { get; set; }

		[JsonProperty("name")]
		public string Name { get; set; }

		[JsonProperty("city")]
		public string City { get; set; }

		[JsonProperty("country")]
		public string Country { get; set; }

		[JsonProperty("goals")]
		public int Goals { get; set; }

		[JsonProperty("logotype")]
		public Guid Logotype { get; set; }
	}

	public class GetResponse : ResponseBase
	{
		[JsonProperty("id")]
		public int Id { get; set; }
		
		[JsonProperty("tourneyId", NullValueHandling = NullValueHandling.Ignore)]
		public int TourneyId { get; set; }

		[JsonProperty("home")]
		public GetResponseTeam Home { get; set; }

		[JsonProperty("guest")]
		public GetResponseTeam Guest { get; set; }
	}

	public class GetResponseTeam
	{
		[JsonProperty("id")]
		public int Id { get; set; }

		[JsonProperty("name")]
		public string Name { get; set; }

		[JsonProperty("city")]
		public string City { get; set; }

		[JsonProperty("country")]
		public string Country { get; set; }
		
		[JsonProperty("logotype")]
		public Guid Logotype { get; set; }

		[JsonProperty("goals")]
		public IEnumerable<GetResponseTeamGoal> Goals { get; set; }

		[JsonProperty("players")]
		public IEnumerable<GetResponsePlayer> Players { get; set; }
	}

	public class GetResponseTeamGoal
	{
		[JsonProperty("goalDt")]
		public DateTimeOffset GoalDt { get; set; }

		[JsonProperty("authorId")]
		public int AuthorId { get; set; }

		[JsonProperty("assistantId", NullValueHandling = NullValueHandling.Ignore)]
		public int? AssistantId { get; set; }
	}

	public class GetResponsePlayer
	{
		[JsonProperty("id")]
		public int Id { get; set; }

		[JsonProperty("firstName")]
		public string FisrtName { get; set; }

		[JsonProperty("lastName")]
		public string LastName { get; set; }
		
		[JsonProperty("number")]
		public int Number { get; set; }

		[JsonProperty("position")]
		[JsonConverter(typeof(StringEnumConverter))]
		public Position Position { get; set; }
	}
}
