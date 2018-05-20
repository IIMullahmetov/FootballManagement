namespace FootballManagementApi.DAL.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class RemovedLeagues : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("dbo.Matches", "LeagueId", "dbo.Leagues");
            DropForeignKey("dbo.LeagueTeams", "LeagueId", "dbo.Leagues");
            DropForeignKey("dbo.LeagueTeams", "TeamId", "dbo.Teams");
            DropIndex("dbo.Matches", new[] { "TourneyId" });
            DropIndex("dbo.Matches", new[] { "LeagueId" });
            DropIndex("dbo.LeagueTeams", new[] { "LeagueId" });
            DropIndex("dbo.LeagueTeams", new[] { "TeamId" });
            AddColumn("dbo.Players", "Image", c => c.Guid(nullable: false));
            AddColumn("dbo.Tourneys", "EndDt", c => c.DateTime(nullable: false));
            AlterColumn("dbo.Matches", "TourneyId", c => c.Int(nullable: false));
            CreateIndex("dbo.Matches", "TourneyId");
            DropColumn("dbo.Matches", "LeagueId");
            DropTable("dbo.Leagues");
            DropTable("dbo.LeagueTeams");
        }
        
        public override void Down()
        {
            CreateTable(
                "dbo.LeagueTeams",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Score = c.Int(nullable: false),
                        LeagueId = c.Int(nullable: false),
                        TeamId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.Leagues",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Name = c.String(),
                        StartDt = c.DateTime(nullable: false),
                    })
                .PrimaryKey(t => t.Id);
            
            AddColumn("dbo.Matches", "LeagueId", c => c.Int());
            DropIndex("dbo.Matches", new[] { "TourneyId" });
            AlterColumn("dbo.Matches", "TourneyId", c => c.Int());
            DropColumn("dbo.Tourneys", "EndDt");
            DropColumn("dbo.Players", "Image");
            CreateIndex("dbo.LeagueTeams", "TeamId");
            CreateIndex("dbo.LeagueTeams", "LeagueId");
            CreateIndex("dbo.Matches", "LeagueId");
            CreateIndex("dbo.Matches", "TourneyId");
            AddForeignKey("dbo.LeagueTeams", "TeamId", "dbo.Teams", "Id");
            AddForeignKey("dbo.LeagueTeams", "LeagueId", "dbo.Leagues", "Id");
            AddForeignKey("dbo.Matches", "LeagueId", "dbo.Leagues", "Id");
        }
    }
}
