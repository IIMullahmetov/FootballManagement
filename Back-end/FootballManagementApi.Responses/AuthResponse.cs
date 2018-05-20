using System;
using FootballManagementApi.Enums;
using FootballManagementApi.Responses;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace FootballManagementApi.AuthResponse
{
    public class LoginResposne : ResponseBase
    {
        [JsonProperty("accessToken")]
        public string AccessToken { get; set; }

        [JsonProperty("refreshToken")]
        public Guid RefreshToken { get; set; }

		[JsonProperty("role")]
		[JsonConverter(typeof(StringEnumConverter))]
		public Role Role { get; set; }
    }
}
