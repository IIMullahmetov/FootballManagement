using System;
using System.Collections.Generic;
using FootballManagementApi.Responses;
using Newtonsoft.Json;

namespace FootballManagementApi.PlayerResponses
{
    public class GetResponse : ResponseBase
    {
        [JsonProperty("id")]
        public int Id { get; set; }

        [JsonProperty("firstName")]
        public string FirstName { get; set; }

        [JsonProperty("lastName")]
        public string LastName { get; set; }

        [JsonProperty("age")]
        public int Age { get; set; }

        [JsonProperty("image")]
        public Guid Image { get; set; }

		[JsonProperty("teamId")]
		public int TeamId { get; set; }

        [JsonProperty("team")]
        public string Team { get; set; }

        [JsonProperty("matchesCount")]
        public int MatchesCount { get; set; }

        [JsonProperty("winsCount")]
        public int WinsCount { get; set; }

		[JsonProperty("goalsCount")]
		public int GoalsCount { get; set; }

		[JsonProperty("assistsCount")]
		public int AssistsCount { get; set; }

		[JsonProperty("birthDt")]
		public string BirthDt { get; set; }
    }

	public class MatchGetListResponse : PagingResponse
	{
		public MatchGetListResponse(Paging paging) : base(paging) { }

		[JsonProperty("items")]
		public IEnumerable<MathGetListItem> Items { get; set; }
	}

	public class MathGetListItem
	{
		[JsonProperty("id")]
		public int Id { get; set; }

		[JsonProperty("date")]
		public string Date { get; set; }

		[JsonProperty("goals")]
		public int Goals { get; set; }

		[JsonProperty("assists")]
		public int Assists { get; set; }

		[JsonProperty("redCards")]
		public int RedCards { get; set; }

		[JsonProperty("yellowCards")]
		public int YellowCards { get; set; }

		[JsonProperty("minutes")]
		public int Minutes { get; set; }

		[JsonProperty("goalShots")]
		public int GoalShots { get; set; }

		[JsonProperty("alignmentShots")]
		public int AlignmentShots { get; set; }

		[JsonProperty("cornerShots")]
		public int CornerShots { get; set; }

		[JsonProperty("offsideShots")]
		public int Offside { get; set; }

		[JsonProperty("home")]
		public MatchGetListTeamInfo Home { get; set; }

		[JsonProperty("guest")]
		public MatchGetListTeamInfo Guest { get; set; }

		[JsonProperty("tourneyInfo")]
		public MatchGetListTourneyInfo TourneyInfo { get; set; }
	}

	public class MatchGetListTeamInfo
	{
		[JsonProperty("id")]
		public int Id { get; set; }

		[JsonProperty("name")]
		public string Name { get; set; }

		[JsonProperty("goals")]
		public int Goals { get; set; }
	}

	public class MatchGetListTourneyInfo
	{
		[JsonProperty("id")]
		public int Id { get; set; }

		[JsonProperty("name")]
		public string Name { get; set; }
	}
}
