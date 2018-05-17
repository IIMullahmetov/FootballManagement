namespace FootballManagementApi.DAL.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class GlobalStructChanging : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("dbo.MatchPlayers", "Match_Id", "dbo.Matches");
            DropForeignKey("dbo.MatchPlayers", "Player_Id", "dbo.Players");
            DropIndex("dbo.MatchPlayers", new[] { "Match_Id" });
            DropIndex("dbo.MatchPlayers", new[] { "Player_Id" });
			DropTable("dbo.MatchPlayers");
			CreateTable(
                "dbo.MatchPlayers",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Status = c.Byte(nullable: false),
                        MatchId = c.Int(nullable: false),
                        PlayerId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Matches", t => t.MatchId, cascadeDelete: true)
                .ForeignKey("dbo.Players", t => t.PlayerId, cascadeDelete: true)
                .Index(t => t.MatchId)
                .Index(t => t.PlayerId);
            
            CreateTable(
                "dbo.Cards",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        CreateDt = c.DateTimeOffset(nullable: false, precision: 7),
                        Type = c.Byte(nullable: false),
                        PlayerId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.MatchPlayers", t => t.PlayerId, cascadeDelete: true)
                .Index(t => t.PlayerId);
            
            CreateTable(
                "dbo.Shots",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Type = c.Byte(nullable: false),
                        MatchId = c.Int(nullable: false),
                        TeamId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Matches", t => t.MatchId, cascadeDelete: true)
                .ForeignKey("dbo.Teams", t => t.TeamId, cascadeDelete: true)
                .Index(t => t.MatchId)
                .Index(t => t.TeamId);
            
            AddColumn("dbo.Matches", "HomeData_Possession", c => c.Double(nullable: false));
            AddColumn("dbo.Matches", "HomeData_Fouls", c => c.Int(nullable: false));
            AddColumn("dbo.Matches", "GuestData_Possession", c => c.Double(nullable: false));
            AddColumn("dbo.Matches", "GuestData_Fouls", c => c.Int(nullable: false));
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.MatchPlayers", "PlayerId", "dbo.Players");
            DropForeignKey("dbo.MatchPlayers", "MatchId", "dbo.Matches");
            DropForeignKey("dbo.Shots", "TeamId", "dbo.Teams");
            DropForeignKey("dbo.Shots", "MatchId", "dbo.Matches");
            DropForeignKey("dbo.Cards", "PlayerId", "dbo.MatchPlayers");
            DropIndex("dbo.Shots", new[] { "TeamId" });
            DropIndex("dbo.Shots", new[] { "MatchId" });
            DropIndex("dbo.Cards", new[] { "PlayerId" });
            DropIndex("dbo.MatchPlayers", new[] { "PlayerId" });
            DropIndex("dbo.MatchPlayers", new[] { "MatchId" });
            DropColumn("dbo.Matches", "GuestData_Fouls");
            DropColumn("dbo.Matches", "GuestData_Possession");
            DropColumn("dbo.Matches", "HomeData_Fouls");
            DropColumn("dbo.Matches", "HomeData_Possession");
			CreateTable(
				"dbo.MatchPlayers",
				c => new
				{
					Match_Id = c.Int(nullable: false),
					Player_Id = c.Int(nullable: false),
				})
				.PrimaryKey(t => new { t.Match_Id, t.Player_Id });
			CreateIndex("dbo.MatchPlayers", "Player_Id");
            CreateIndex("dbo.MatchPlayers", "Match_Id");
            AddForeignKey("dbo.MatchPlayers", "Player_Id", "dbo.Players", "Id", cascadeDelete: true);
            AddForeignKey("dbo.MatchPlayers", "Match_Id", "dbo.Matches", "Id", cascadeDelete: true);
        }
    }
}
