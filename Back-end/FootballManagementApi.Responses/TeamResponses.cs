using FootballManagementApi.Responses;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace FootballManagementApi.TeamResponses
{
	public class GetResponse : ResponseBase
	{
		[JsonProperty("id")]
		public int Id { get; set; }

		[JsonProperty("name")]
		public string Name { get; set; }

		[JsonProperty("logo")]
		public Guid Logo { get; set; }

		[JsonProperty("players")]
		public IEnumerable<TeamPlayer> Players { get; set; }
	}

	public class TeamPlayer
	{
		[JsonProperty("id")]
		public int Id { get; set; }

		[JsonProperty("firstName")]
		public string FirstName { get; set; }

		[JsonProperty("lastName")]
		public string LastName { get; set; }

		[JsonProperty("image")]
		public Guid Image { get; set; }
	}
}
