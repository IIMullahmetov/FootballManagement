namespace FootballManagementApi.Services
{
    public interface IPasswordValidator
    {
        bool IsValid(string password);
    }
}
