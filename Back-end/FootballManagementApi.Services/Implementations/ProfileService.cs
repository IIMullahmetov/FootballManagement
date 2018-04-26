using System;
using System.Threading.Tasks;
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

        public ProfileService(IEmailValidator emailValidator, IFileManager fileManager, IMailSender mailSender)
        {
            _emailValidator = emailValidator;
            _fileManager = fileManager;
            _mailSender = mailSender;
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

        public async Task ChangeImageAsync(User user, byte[] image)
        {
            //TODO Change message
            if (image == null) throw new ActionCannotBeExecutedException("HZ");

            Guid guid = Guid.NewGuid();
            string path = PathHelper.GeneratePath(guid);
            await _fileManager.WriteFileAsync(image, path);

            user.Image = guid;
        }

        public async Task EditAsync(User user, string firstName, string lastName, Gender? gender)
        {
            ValidateData(firstName, lastName, gender);

            user.FirstName = firstName;
            user.LastName = lastName;
            user.Gender = gender;
        }

        //TODO Implement him
        private void ValidateData(string firstName, string lastName, Gender? gender)
        {

        }
    }
}
