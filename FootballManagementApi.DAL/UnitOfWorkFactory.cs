using System;

namespace FootballManagementApi.DAL
{
	public class UnitOfWorkFactory : IUnitOfWorkFactory
	{
		public IUnitOfWork GetUnitOfWork() => new UnitOfWork<Context>();
	}
}
