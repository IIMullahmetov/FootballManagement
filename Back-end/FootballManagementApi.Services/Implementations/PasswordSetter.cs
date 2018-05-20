using FootballManagementApi.DAL.Models;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.Helpers;
using FootballManagementApi.Resources;
using System.Text.RegularExpressions;

namespace FootballManagementApi.Services.Implementations
{
    public class PasswordSetter : IPasswordSetter
    {
        public void SetPassword(User user, string password, string confirm)
        {
            if (password != confirm) throw new ActionCannotBeExecutedException(ExceptionMessages.PasswordsNotMatch);
            Validate(password, confirm);

            byte[] salt = PasswordHelper.GenerateSalt();
            byte[] pass = PasswordHelper.HashPassword(password, salt);

            user.Password = pass;
            user.Salt = salt;
        }

        private void Validate(string password, string confirm)
        {
            var hasNumber = new Regex(@"[0-9]+");
            var hasUpperChar = new Regex(@"[A-Z]+");
            var has8Char = new Regex(@".{8,}");

            var isValidated = hasNumber.IsMatch(password) &&
                hasUpperChar.IsMatch(password) &&
                has8Char.IsMatch(password);

            if (!isValidated)
                throw new ActionCannotBeExecutedException(ExceptionMessages.InvalidPassword);

            if (!password.Equals(confirm))
                throw new ActionCannotBeExecutedException(ExceptionMessages.PasswordsNotMatch);
        }
    }
}
