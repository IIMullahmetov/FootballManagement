using FootballManagementApi.Enums;

namespace FootballManagementApi.Auth
{
    public class Identity : IIdentity
    {
        public int Id { get; set; }

        public Role Role { get; set; }

        public string Email { get; set; }

        public string Name { get; set; }

        public string AuthenticationType { get; set; }

        public bool IsAuthenticated { get; set; }
    }
}
