using System;
using Newtonsoft.Json;

namespace FootballManagementApi.PlayerResponses
{
    public class GetResponse
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

        [JsonProperty("team")]
        public string Team { get; set; }

        [JsonProperty("matchesCount")]
        public int MatchesCount { get; set; }

        [JsonProperty("winsCount")]
        public int WinsCount { get; set; }
    }
}
