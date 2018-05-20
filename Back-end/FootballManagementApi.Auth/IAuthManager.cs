namespace FootballManagementApi.Auth
{
    public interface IAuthManager
    {
        IPrincipal GetPrincipal(string header);
    }
}
