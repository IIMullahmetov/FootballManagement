using FootballManagementApi.DAL.Models;
using System.Data.Entity;
using System.Data.Entity.ModelConfiguration;
using System.Diagnostics;

namespace FootballManagementApi.DAL
{
	public class Context : DbContext
	{
		static Context() => Database.SetInitializer(new MigrateDatabaseToLatestVersion<Context, Migrations.Configuration>());

		//TODO Disable ChangeTracker
		public Context() : base("FM")
		{
			//string t = Database.Connection.ConnectionString;
			for (int i = 0; i < 1000; i++)
			{
				Trace.TraceInformation(Database.Connection.ConnectionString);
			}
		}

		protected override void OnModelCreating(DbModelBuilder modelBuilder)
		{
			modelBuilder.Configurations.Add(new EntityTypeConfiguration<User>());
		}
	}
}
