using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using FootballManagementApi.Auth;

namespace FootballManagementApi
{
    public class AuthOption : IAuthOption
    {
        public int RefreshTokenLife => 10000000;

        public int TokenLife => 10000;

        public string Secret => "Somefuckinsecreteptablya";
    }
}