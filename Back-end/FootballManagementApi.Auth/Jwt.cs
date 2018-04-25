using FootballManagementApi.Enums;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace FootballManagementApi.Auth
{
    public class Jwt
    {
        [JsonProperty("id")]
        public int Id { get; set; }

        [JsonProperty("role")]
        public Role Role { get; set; }

        [JsonProperty("email")]
        public string Email { get; set; }

        [JsonProperty("loginType")]
        [JsonConverter(typeof(StringEnumConverter))]
        public LoginType LoginType { get; set; }

        [JsonProperty("expireAt")]
        public int ExpireAt { get; set; }
    }
}
