using System;
using FootballManagementApi.Enums;
using FootballManagementApi.Responses;
using Newtonsoft.Json;

namespace FootballManagementApi.ProfileResponses
{
    public class GetResponse : ResponseBase
    {
        [JsonProperty("email")]
        public string Email { get; set; }

        [JsonProperty("firstName")]
        public string FirstName { get; set; }

        [JsonProperty("lastName")]
        public string LastName { get; set; }

        [JsonProperty("gender")]
        public Gender? Gender { get; set; }

        [JsonProperty("image")]
        public Guid? Image { get; set; }
    }
}
