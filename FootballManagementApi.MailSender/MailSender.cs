using System.Threading.Tasks;
using System.Net.Mail;
using System.Configuration;
using System;

namespace FootballManagementApi.MailSender
{
	public class MailSender : IMailSender
	{
		public async Task SendAsync(Letter letter)
		{
		    string from = "no-reply@" + ConfigurationManager.AppSettings["HostName"];
		    string host = ConfigurationManager.AppSettings["SmtpHost"];
		    int port = Convert.ToInt32(ConfigurationManager.AppSettings["SmtpPort"]);

		    using (SmtpClient smtp = new SmtpClient(host, port))
		    {
			foreach (string email in letter.Email)
			{
			    MailMessage message = new MailMessage(from, email, letter.Topic, letter.Body);
			    await smtp.SendMailAsync(message);
			}
		    }
		}
	}
}
