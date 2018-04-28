using FootballManagementApi.DAL.Models;
using System.Data.Entity;
using System.Data.Entity.ModelConfiguration;

namespace FootballManagementApi.DAL
{
	public class Context : DbContext
	{
		static Context() => Database.SetInitializer(new MigrateDatabaseToLatestVersion<Context, Migrations.Configuration>());
		
		//TODO Disable ChangeTracker
		public Context() : base("FM")
		{
			
		}

		protected override void OnModelCreating(DbModelBuilder modelBuilder)
		{
			modelBuilder.Configurations.Add(new EntityTypeConfiguration<Comment>());
			modelBuilder.Configurations.Add(new EntityTypeConfiguration<Goal>());

			modelBuilder.Configurations.Add(new EntityTypeConfiguration<League>());
			modelBuilder.Entity<League>().HasMany(l => l.Matches).WithOptional(m => m.League).WillCascadeOnDelete(false);
			modelBuilder.Entity<League>().HasMany(l => l.Teams).WithRequired(t => t.League).WillCascadeOnDelete(false);

			modelBuilder.Configurations.Add(new EntityTypeConfiguration<LeagueTeam>());

			modelBuilder.Configurations.Add(new EntityTypeConfiguration<Match>());
			modelBuilder.Entity<Match>().HasMany(m => m.Goals).WithRequired(g => g.Match).WillCascadeOnDelete(false);

			modelBuilder.Configurations.Add(new EntityTypeConfiguration<Player>());
			modelBuilder.Entity<Player>().HasMany(p => p.Goals).WithRequired(g => g.Author).WillCascadeOnDelete(false);
			modelBuilder.Entity<Player>().HasMany(p => p.Assists).WithOptional(g => g.Assistant).WillCascadeOnDelete(false);
			
			modelBuilder.Configurations.Add(new EntityTypeConfiguration<Post>());
			modelBuilder.Entity<Post>().HasMany(p => p.Items).WithRequired(i => i.Post).WillCascadeOnDelete(false);
			modelBuilder.Entity<Post>().HasMany(p => p.Comments).WithRequired(c => c.Post).WillCascadeOnDelete(false);
            modelBuilder.Entity<Post>().HasMany(p => p.Likes).WithMany(u => u.Likes).Map(p =>
            {
                p.ToTable("PostLikes");
            });
            modelBuilder.Entity<Post>().HasMany(p => p.Dislikes).WithMany(p => p.Dislikes).Map(p =>
            {
                p.ToTable("PostDislikes");
            });

			modelBuilder.Configurations.Add(new EntityTypeConfiguration<PostItem>());

			modelBuilder.Configurations.Add(new EntityTypeConfiguration<Team>());
			modelBuilder.Entity<Team>().HasMany(t => t.Players).WithRequired(p => p.Team).WillCascadeOnDelete(false);
			modelBuilder.Entity<Team>().HasMany(t => t.Tourneys).WithRequired(t => t.Team).WillCascadeOnDelete(false);
			modelBuilder.Entity<Team>().HasMany(t => t.Leagues).WithRequired(t => t.Team).WillCascadeOnDelete(false);
			modelBuilder.Entity<Team>().HasMany(t => t.Goals).WithRequired(t => t.Team).WillCascadeOnDelete(false);
			modelBuilder.Entity<Team>().HasMany(t => t.HomeMathces).WithRequired(m => m.Home).WillCascadeOnDelete(false);
			modelBuilder.Entity<Team>().HasMany(t => t.GuestMatches).WithRequired(m => m.Guest).WillCascadeOnDelete(false);

			modelBuilder.Configurations.Add(new EntityTypeConfiguration<Tourney>());
			modelBuilder.Entity<Tourney>().HasMany(t => t.Matches).WithOptional(m => m.Tourney).WillCascadeOnDelete(false);
			modelBuilder.Entity<Tourney>().HasMany(t => t.Teams).WithRequired(t => t.Tourney).WillCascadeOnDelete(false);

			modelBuilder.Configurations.Add(new EntityTypeConfiguration<TourneyTeam>());

			modelBuilder.Configurations.Add(new EntityTypeConfiguration<User>());
			modelBuilder.Entity<User>().HasMany(u => u.Posts).WithRequired(p => p.User).WillCascadeOnDelete(false);
			modelBuilder.Entity<User>().HasMany(u => u.Comments).WithRequired(c => c.User).WillCascadeOnDelete(false);
            //modelBuilder.Entity<User>().HasOptional(u => u.Registration).WithOptionalPrincipal(r => r.User);
            

            modelBuilder.Configurations.Add(new EntityTypeConfiguration<Registration>());
            modelBuilder.Entity<Registration>().HasOptional(u => u.User).WithOptionalDependent(r => r.Registration);
            //modelBuilder.Entity<Registration>().HasRequired(r => r.User).WithOptional()
            //modelBuilder.Entity<Registration>().HasRequired(r => r.User).WithOptional(u => u.Registration);
        }
	}
}
