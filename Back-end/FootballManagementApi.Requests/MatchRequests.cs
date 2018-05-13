using FootballManagementApi.Enums;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using System;
using System.Collections.Generic;

namespace FootballManagementApi.MatchRequests
{

	public class ScoreRequest
	{
		[JsonProperty("authorId")]
		public int AuthorId { get; set; }

		[JsonProperty("assistantId")]
		public int? AssistantId { get; set; }
	}

	public class CreateRequest
	{
		[JsonProperty("homeId")]
		public int HomeId { get; set; }

		[JsonProperty("guestId")]
		public int GuestId { get; set; }

		[JsonProperty("startDt")]
		public DateTimeOffset StartDt { get; set; }

		[JsonProperty("status")]
		[JsonConverter(typeof(StringEnumConverter))]
		public MatchStatus Status { get; set; }

		[JsonProperty("homePlayers")]
		public IEnumerable<int> HomePlayers { get; set; }

		[JsonProperty("guestPlayers")]
		public IEnumerable<int> GuestPlayers { get; set; }
	}
}
