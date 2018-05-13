using Newtonsoft.Json;
using System;

namespace FootballManagementApi.AuthRequests
{
    public class LoginRequest
    {
        [JsonProperty("email")]
        public string Email { get; set; }

        [JsonProperty("password")]
        public string Password { get; set; }
    }

	public class RefreshTokenRequest
	{
		[JsonProperty("refreshToken")]
		public Guid RefreshToken { get; set; }
	}
}
