using System.Threading.Tasks;

namespace FootballManagementApi.MailSender
{
	public interface IMailSender
	{
		Task SendAsync(Letter letter);
	}
}
