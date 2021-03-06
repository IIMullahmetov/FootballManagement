﻿using FootballManagementApi.Enums;

namespace FootballManagementApi.Auth
{
    public interface IIdentity : System.Security.Principal.IIdentity
    {
        int Id { get; set; }

        string Email { get; set; }

        Role Role { get; set; }        
    }
}
