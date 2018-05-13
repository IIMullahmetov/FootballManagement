using System;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using FootballManagementApi.DAL;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.Enums;
using FootballManagementApi.FileStorage;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.Helpers;
using FootballManagementApi.MailSender;
using FootballManagementApi.Resources;

namespace FootballManagementApi.Services.Implementations
{
    public class ProfileService : IProfileService
    {
        private IEmailValidator _emailValidator;
        private IFileManager _fileManager;
        private IMailSender _mailSender;
        private IPasswordSetter _passwordSetter;
        private IUnitOfWork _unitOfWork;

        public ProfileService(IEmailValidator emailValidator, IFileManager fileManager, IMailSender mailSender, IPasswordSetter passwordSetter, IUnitOfWork unitOfWork)
        {
            _emailValidator = emailValidator;
            _fileManager = fileManager;
            _mailSender = mailSender;
            _passwordSetter = passwordSetter;
            _unitOfWork = unitOfWork;
        }

        public async Task ChangeEmailAsync(User user, string email)
        {
            //Юзеры зареганные чз гугл не могут поменять почту
            if (user.Registration.Type == RegistrationType.Google) throw new ActionCannotBeExecutedException(ExceptionMessages.ForbiddenToChangeEmail);

            await _emailValidator.ValidateToRegistrationAsync(email);

            //TODO ChangeBody
            await _mailSender.SendAsync(new Letter { Body = "", Email = new string[] { email }, Topic = "email Changing" });

            user.Email = email;
        }

        public async Task ChangeImageAsync(User user, int id)
        {
            File image = await _unitOfWork.GetFileRepository().SelectByIdAsync(id)
                ?? throw new ActionCannotBeExecutedException(ExceptionMessages.FileNotFound);

            user.Image = image.Guid;
        }

        public void ChangePassword(User user, string password, string confirm)
        {
            if (user.Registration.Type == RegistrationType.Google) throw new ActionCannotBeExecutedException(ExceptionMessages.ForbiddenToChangeEmail);

            _passwordSetter.SetPassword(user, password, confirm);
        }

        public void Edit(User user, string firstName, string lastName, Gender? gender)
        {
            ValidateData(firstName, lastName);

            user.FirstName = firstName;
            user.LastName = lastName;
            user.Gender = gender;
        }

        private void ValidateData(string firstName, string lastName)
        {
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
        }
    }
}
