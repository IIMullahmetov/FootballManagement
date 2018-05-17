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

		[JsonProperty("homePlayersMain")]
		public IEnumerable<int> HomePlayersMain { get; set; }

		[JsonProperty("guestPlayersMain")]
		public IEnumerable<int> GuestPlayersMain { get; set; }

		[JsonProperty("homePlayersSpare")]
		public IEnumerable<int> HomePlayersSpare { get; set; }

		[JsonProperty("guestPlayersSpare")]
		public IEnumerable<int> GuestPlayersSpare { get; set; }
	}
}
