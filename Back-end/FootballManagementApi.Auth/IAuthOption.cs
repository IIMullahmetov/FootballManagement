namespace FootballManagementApi.Auth
{
    public interface IAuthOption
    {
        int RefreshTokenLife { get; }

        int TokenLife { get; }

        string Secret { get; }
    }
}
