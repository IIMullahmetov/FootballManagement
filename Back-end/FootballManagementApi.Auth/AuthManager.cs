using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace FootballManagementApi.Auth
{
    public class AuthManager : IAuthManager
    {
        private IAuthOption _authOption;

        public AuthManager(IAuthOption authOption)
        {
            _authOption = authOption;
        }

        public IPrincipal GetPrincipal(string header)
        {
            string json = JsonWebToken.Decode(header, _authOption.Secret);
            Jwt jwt = JObject.Parse(json).ToObject<Jwt>();

            return new Principal(jwt);
        }
    }
}
