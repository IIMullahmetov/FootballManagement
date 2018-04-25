using System;
using FootballManagementApi.Responses;
using Newtonsoft.Json;

namespace FootballManagementApi.AuthResponse
{
    public class LoginResposne : ResponseBase
    {
        [JsonProperty("accessToken")]
        public string AccessToken { get; set; }

        [JsonProperty("refreshToken")]
        public Guid RefreshToken { get; set; }
    }
}
