using System.Linq;
using System.Threading.Tasks;
using Microsoft.Owin;

namespace FootballManagementApi.Auth
{
    public class AuthMiddleware : OwinMiddleware
    {
        private IAuthManager _authManager;
        private const string _authorization = "Authorization";
        private const string _bearer = "Bearer";

        public AuthMiddleware(OwinMiddleware middleware, IAuthManager authManager) : base(middleware)
        {
            _authManager = authManager;
        }

        public override Task Invoke(IOwinContext context)
        {
            try
            {
                string authHeader = context.Request.Headers.FirstOrDefault(h => h.Key == _authorization).Value.FirstOrDefault();
                authHeader = authHeader.Substring(_bearer.Length).Trim();
                context.Request.User = _authManager.GetPrincipal(authHeader) as Principal;
            }
            catch { }
            return Next.Invoke(context);
        }
    }
}
