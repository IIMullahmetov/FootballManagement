namespace FootballManagementApi.DAL
{
	public interface IUnitOfWorkFactory
	{
		IUnitOfWork GetUnitOfWork();
	}
}
