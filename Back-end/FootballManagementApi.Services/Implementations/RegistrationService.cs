using System;
using System.Configuration;
using System.Net.Mail;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using FootballManagementApi.DAL;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.Enums;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.MailSender;
using FootballManagementApi.Resources;

namespace FootballManagementApi.Services.Implementations
{
    public class RegistrationService : IRegistrationService
    {
        private IEmailValidator _emailValidator;
        private IUnitOfWork _unitOfWork;
        private IMailSender _mailSender;
        private IPasswordSetter _passwordSetter;
        private static readonly string _currentHost;

        static RegistrationService()
        {
            _currentHost = ConfigurationManager.AppSettings["Host"];
        }

        public RegistrationService(IEmailValidator emailValidator,IUnitOfWork unitOfWork, IMailSender mailSender, IPasswordSetter passwordSetter)
        {
            _emailValidator = emailValidator;
            _unitOfWork = unitOfWork;
            _mailSender = mailSender;
            _passwordSetter = passwordSetter;
        }

        public async Task<Registration> RegisterAsync(RegistrationType registrationType, string email = null, string password = null, string confirm = null, string firstName = null,
             string lastName = null, DateTime? birthDt = null, Gender? gender = null, Role role = Role.User)
        {
            ValidateData(email, firstName, lastName, birthDt);

            Registration registration = new Registration
            {
                CreateDt = DateTimeOffset.Now,
                Guid = Guid.NewGuid(),
                Status = RegistrationStatus.Pending,
                Type = registrationType
            };

            if (registrationType == RegistrationType.Email)
            {
                ValidatePassword(password, confirm);
                User user = new User
                {
                    FirstName = firstName,
                    LastName = lastName,
                    Role = role,
                    Gender = gender,
                    Registration = registration,
                    Status = UserStatus.Pending,
                    Email = email,
                };
                _passwordSetter.SetPassword(user, password, confirm);
				_unitOfWork.GetUserRepository().Insert(user);
                string emailBody = MailTemplates.Confirmation.Replace("[Confirmation_Link]", _currentHost + "registration/confirm?guid=" + registration.Guid);
                await _mailSender.SendAsync(new Letter { Topic = "Registration confirmation", Email = new string[] { user.Email }, Body = emailBody });
            }

            if (registrationType == RegistrationType.Google)
            {
                User user = new User
                {
                    FirstName = firstName,
                    LastName = lastName,
                    Role = Role.User,
                    Gender = gender,
                    Registration = registration,
                    Status = UserStatus.Active,
                    Email = email,
                };
                registration.Status = RegistrationStatus.Accepted;
                _unitOfWork.GetUserRepository().Insert(user);
                string emailBody = MailTemplates.Confirmation.Replace("[Confirmation_Link]", _currentHost + "registration/confirm?guid=" + registration.Guid);
                await _mailSender.SendAsync(new Letter { Topic = "Registration confirmation", Email = new string[] { user.Email }, Body = emailBody });
            }

            return registration;
        }

        public async Task<User> ConfirmAsync(Guid guid)
        {
            Registration registration = await _unitOfWork.GetRegistrationRepository().SelectFirstOrDefaultAsync(r => r.Guid == guid && r.Status == RegistrationStatus.Pending)
                ?? throw new ActionCannotBeExecutedException(ExceptionMessages.InvalidRegistrationGuid);

            registration.User.Status = UserStatus.Active;
            registration.Status = RegistrationStatus.Accepted;
            return registration.User;
        }

        private void ValidateData(string email, string firstName = null, string lastName = null, DateTime? birthDt = null)
        {
            try
            {
                MailAddress mailAddress = new MailAddress(email);
            }
            catch (Exception ex)
            {
                throw new ActionCannotBeExecutedException(ExceptionMessages.InvalidEmailFormat);
            }

            if (firstName != null)
            {
                var isName = new Regex(@"^[A-Z][a-z]+$");

                if (!isName.IsMatch(firstName))
                    throw new ActionCannotBeExecutedException(ExceptionMessages.InvalidFirstName);
            }

            if (lastName != null)
            {
                var isName = new Regex(@"^[A-Z][a-z]+$");

                if (!isName.IsMatch(lastName))
                    throw new ActionCannotBeExecutedException(ExceptionMessages.InvalidLastName);
            }

            if (birthDt != null)
                if (birthDt >= DateTime.Today.AddYears(-16))
                    throw new ActionCannotBeExecutedException(ExceptionMessages.InvalidBirthDate);
        }

        private void ValidatePassword(string password, string confirm)
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
