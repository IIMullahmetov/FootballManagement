using System;

namespace FootballManagementApi.Auth
{
    public class Principal : IPrincipal
    {
        private readonly DateTime _startDt = new DateTime(2018, 1, 1, 0, 0, 0, 0, DateTimeKind.Utc);

        public Principal(Jwt jwt)
        {
            ExpireAt = _startDt.AddSeconds(jwt.ExpireAt);
            Identity = new Identity
            {
                Id = jwt.Id,
                Role = jwt.Role,
                AuthenticationType = jwt.LoginType.ToString(),
                Email = jwt.Email
            };
        }

        public System.Security.Principal.IIdentity Identity { get; }

        public bool IsInRole(string role) => throw new NotImplementedException();

        public DateTimeOffset ExpireAt { get; set; }
    }
}
