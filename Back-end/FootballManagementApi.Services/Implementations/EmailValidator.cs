using System.Text.RegularExpressions;
using System.Threading.Tasks;
using FootballManagementApi.DAL;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.Resources;

namespace FootballManagementApi.Services.Implementations
{
    public class EmailValidator : IEmailValidator
    {
        private IUnitOfWork _unitOfWork;

        public EmailValidator(IUnitOfWork unitOfWork)
        {
            _unitOfWork = unitOfWork;
        }

        public async Task ValidateAsync(string email)
        {
            if (!IsValidFormat(email))
            {
                throw new ActionCannotBeExecutedException(ExceptionMessages.InvalidEmailFormat);
            }
        }

        public async Task ValidateToRegistrationAsync(string email)
        {
            if (!IsValidFormat(email))
            {
                throw new ActionCannotBeExecutedException(ExceptionMessages.InvalidEmailFormat);
            }
            if (await _unitOfWork.GetUserRepository().AnyAsync(u => u.Email == email && u.Status == Enums.UserStatus.Active))
            {
                throw new ActionCannotBeExecutedException(ExceptionMessages.EmailAlreadyUse);
            }
        }

        private bool IsValidFormat(string email)
        {
            Regex regex = new Regex(@"^([\w\.\-]+)@([\w\-]+)((\.(\w){2,3})+)$");
            Match match = regex.Match(email);
            return match.Success;
        }
    }
}
