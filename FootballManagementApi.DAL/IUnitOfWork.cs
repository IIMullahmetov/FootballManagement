using System;
using System.Threading.Tasks;

namespace FootballManagementApi.DAL
{
	public interface IUnitOfWork : IDisposable
	{
		
		Task SaveChangesAsync();
	}
}
