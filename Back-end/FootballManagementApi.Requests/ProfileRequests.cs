using FootballManagementApi.Enums;
using Newtonsoft.Json;

namespace FootballManagementApi.ProfileRequests
{
    public class EditRequest
    {
        [JsonProperty("firstName")]
        public string FirstName { get; set; }

        [JsonProperty("lastName")]
        public string LastName { get; set; }

        [JsonProperty("gender")]
        public Gender? Gender { get; set; }
    }

    public class ChangeEmailRequest
    {
        [JsonProperty("email")]
        public string Email { get; set; }
    }

    public class ChangePasswordRequest
    {
        [JsonProperty("password")]
        public string Password { get; set; }

        [JsonProperty("confirmPassword")]
        public string ConfirmPassword { get; set; }
    }
}
