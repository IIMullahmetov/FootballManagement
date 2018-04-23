using System;
using FootballManagementApi.Enums;
using Newtonsoft.Json;

namespace FootballManagementApi.RegistrationRequests
{
    public class RegisterRequest
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

        [JsonProperty("password")]
        [JsonRequired]
        public string Password { get; set; }

        [JsonProperty("birthDay")]
        [JsonRequired]
        public DateTime BirthDay { get; set; }

        [JsonProperty("gender")]
        public Gender? Gender { get; set; }
    }
}
