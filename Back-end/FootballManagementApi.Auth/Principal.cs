using System;

namespace FootballManagementApi.Auth
{
    public class Principal : IPrincipal
    {
        public Principal(Jwt jwt)
        {
            ExpireAt = jwt.ExpireAt;
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
