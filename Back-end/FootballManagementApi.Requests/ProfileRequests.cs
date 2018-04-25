using FootballManagementApi.Enums;

namespace FootballManagementApi.ProfileRequests
{
    public class EditRequest
    {
        public string FirstName { get; set; }

        public string LastName { get; set; }

        public Gender? Gender { get; set; }
    }
}
