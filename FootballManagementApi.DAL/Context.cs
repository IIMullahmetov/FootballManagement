using FootballManagementApi.DAL.Models;
using System.Data.Entity;
using System.Data.Entity.ModelConfiguration;

namespace FootballManagementApi.DAL
{
	public class Context : DbContext
	{
		static Context() => Database.SetInitializer(new MigrateDatabaseToLatestVersion<Context, Migrations.Configuration>());

		public Context() : base("FM")
		{
			var t =this.Database.Connection.ConnectionString;
		}

		protected override void OnModelCreating(DbModelBuilder modelBuilder)
		{
			modelBuilder.Configurations.Add(new EntityTypeConfiguration<User>());
		}
	}
}
