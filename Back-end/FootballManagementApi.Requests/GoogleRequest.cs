using System;
using FootballManagementApi.Enums;
using Newtonsoft.Json;

namespace FootballManagementApi.RegistrationRequests
{
    public class GoogleRequest
    {
        [JsonProperty("email")]
        [JsonRequired]
        public string Email { get; set; }

        [JsonProperty("firstName")]
        [JsonRequired]
        public string FirstName { get; set; }

        [JsonProperty("lastName")]
        [JsonRequired]
        public string LastName { get; set; }
		
        [JsonProperty("gender")]
        public Gender? Gender { get; set; }

        [JsonProperty("googleToken")]
        public string GoogleToken { get; set; }
    }
}