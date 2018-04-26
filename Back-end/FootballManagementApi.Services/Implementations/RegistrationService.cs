using System;
using System.Configuration;
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
             string lastName = null, DateTime? birthDt = null, Gender? gender = null)
        {
            //TODO Implement
            ValidateData();

            Registration registration = new Registration
            {
                CreateDt = DateTimeOffset.Now,
                Guid = Guid.NewGuid(),
                Status = RegistrationStatus.Pending,
                Type = registrationType
            };

            if (registrationType == RegistrationType.Email)
            {
                User user = new User
                {
                    FirstName = firstName,
                    LastName = lastName,
                    Role = Role.User,
                    Gender = gender,
                    Registration = registration,
                    Status = UserStatus.Pending,
                    Email = email,
                };
                //TODO Реализовать отдельно в методе и проверить валидность всех входящих параметров
                _passwordSetter.SetPassword(user, password, confirm);
				_unitOfWork.GetUserRepository().Insert(user);
                //TODO отпралять сверстанную страницу
                await _mailSender.SendAsync(new Letter { Topic = "Reg", Email = new string[] { user.Email }, Body = _currentHost + "registration/confirm?guid=" +  registration.Guid });
            }

            if (registrationType == RegistrationType.Google)
            {
                //TODO Реализовать отдельно в методе и проверить валидность всех входящих параметров

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
                //TODO отпралять сверстанную страницу
                //await _mailSender.SendAsync(new Letter { Topic = "Reg", Email = new string[] { user.Email }, Body = _currentHost + "registration/confirm?guid=" + registration.Guid });
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

        private void ValidateData()
        {

        }
    }
}
