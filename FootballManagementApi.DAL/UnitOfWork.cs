using System;
using System.Data.Entity;
using System.Threading.Tasks;

namespace FootballManagementApi.DAL
{
	internal class UnitOfWork<TContext> : IUnitOfWork where TContext : DbContext, new()
	{
		private bool _isDisposed = false;

		private TContext Context { get; set; }

		internal UnitOfWork() => Context = new TContext();

		public void Dispose()
		{
			if (_isDisposed)
			{
				return;
			}

			Context.Dispose();
			_isDisposed = true;
		}

		public Task SaveChangesAsync()
		{
			Context.ChangeTracker.DetectChanges();
			return Context.SaveChangesAsync();
		}
	}
}
