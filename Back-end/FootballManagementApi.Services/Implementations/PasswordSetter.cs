using FootballManagementApi.DAL.Models;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.Helpers;
using FootballManagementApi.Resources;

namespace FootballManagementApi.Services.Implementations
{
    public class PasswordSetter : IPasswordSetter
    {
        public void SetPassword(User user, string password, string confirm)
        {
            if (password != confirm) throw new ActionCannotBeExecutedException(ExceptionMessages.PasswordsNotMatch);
            Validate(password);

            byte[] salt = PasswordHelper.GenerateSalt();
            byte[] pass = PasswordHelper.HashPassword(password, salt);

            user.Password = pass;
            user.Salt = salt;
        }

        private void Validate(string password)
        {

        }
    }
}
