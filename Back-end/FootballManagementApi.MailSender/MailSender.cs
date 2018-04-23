using System.Threading.Tasks;
using System.Net.Mail;
using System.Configuration;
using System;
using System.Net;

namespace FootballManagementApi.MailSender
{
    public class MailSender : IMailSender
    {
        private static readonly string _from;
        private static readonly string _login;
        private static readonly string _password;

        static MailSender()
        {
            _from = ConfigurationManager.AppSettings["From"];
            _login = ConfigurationManager.AppSettings["Login"];
            _password = ConfigurationManager.AppSettings["Password"];
        }

        public async Task SendAsync(Letter letter)
        {
            string host = ConfigurationManager.AppSettings["HostName"];
            int port = Convert.ToInt32(ConfigurationManager.AppSettings["SmtpPort"]);

            using (SmtpClient smtp = new SmtpClient(host, port))
            {
                smtp.UseDefaultCredentials = false;
                smtp.Credentials = new NetworkCredential(_login, _password);
                smtp.EnableSsl = true;
                foreach (string email in letter.Email)
                {
                    MailMessage message = new MailMessage(new MailAddress(_from), new MailAddress(email))
                    {
                        Body = letter.Body,
                        Subject = letter.Topic
                    };
                    try
                    {
                        await smtp.SendMailAsync(message);
                    }
                    catch
                    {
                        //TODO Attach Logger
                    }
                }
            }
        }
    }
}
