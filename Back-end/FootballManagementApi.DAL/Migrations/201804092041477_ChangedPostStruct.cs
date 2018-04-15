namespace FootballManagementApi.DAL.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class ChangedPostStruct : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Comments",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Body = c.String(),
                        Likes = c.Int(nullable: false),
                        Dislikes = c.Int(nullable: false),
                        UserId = c.Int(nullable: false),
                        PostId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Posts", t => t.PostId)
                .ForeignKey("dbo.Users", t => t.UserId)
                .Index(t => t.UserId)
                .Index(t => t.PostId);
            
            CreateTable(
                "dbo.Posts",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Title = c.String(),
                        Intro = c.String(),
                        Image = c.Guid(),
                        CreateDt = c.DateTimeOffset(nullable: false, precision: 7),
                        Status = c.Byte(nullable: false),
                        UserId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Users", t => t.UserId)
                .Index(t => t.UserId);
            
            CreateTable(
                "dbo.PostItems",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Type = c.Int(nullable: false),
                        Guid = c.Guid(),
                        Link = c.String(),
                        LinkText = c.String(),
                        Text = c.String(),
                        PostId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Posts", t => t.PostId)
                .Index(t => t.PostId);
            
            CreateTable(
                "dbo.Goals",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        MatchId = c.Int(nullable: false),
                        AuthorId = c.Int(nullable: false),
                        TeamId = c.Int(nullable: false),
                        AssistantId = c.Int(),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Players", t => t.AssistantId)
                .ForeignKey("dbo.Players", t => t.AuthorId)
                .ForeignKey("dbo.Teams", t => t.TeamId)
                .ForeignKey("dbo.Matches", t => t.MatchId)
                .Index(t => t.MatchId)
                .Index(t => t.AuthorId)
                .Index(t => t.TeamId)
                .Index(t => t.AssistantId);
            
            CreateTable(
                "dbo.Players",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        FirstName = c.String(),
                        LastName = c.String(),
                        BirthDt = c.DateTime(nullable: false),
                        Status = c.Byte(nullable: false),
                        Team_Id = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Teams", t => t.Team_Id)
                .Index(t => t.Team_Id);
            
            CreateTable(
                "dbo.Teams",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Name = c.String(),
                        City = c.String(),
                        County = c.String(),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.Matches",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        HomeId = c.Int(nullable: false),
                        GuestId = c.Int(nullable: false),
                        Status = c.Byte(nullable: false),
                        StartDt = c.DateTimeOffset(nullable: false, precision: 7),
                        TourneyId = c.Int(),
                        LeagueId = c.Int(),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Leagues", t => t.LeagueId)
                .ForeignKey("dbo.Tourneys", t => t.TourneyId)
                .ForeignKey("dbo.Teams", t => t.GuestId)
                .ForeignKey("dbo.Teams", t => t.HomeId)
                .Index(t => t.HomeId)
                .Index(t => t.GuestId)
                .Index(t => t.TourneyId)
                .Index(t => t.LeagueId);
            
            CreateTable(
                "dbo.Leagues",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Name = c.String(),
                        StartDt = c.DateTime(nullable: false),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.LeagueTeams",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Score = c.Int(nullable: false),
                        LeagueId = c.Int(nullable: false),
                        TeamId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Leagues", t => t.LeagueId)
                .ForeignKey("dbo.Teams", t => t.TeamId)
                .Index(t => t.LeagueId)
                .Index(t => t.TeamId);
            
            CreateTable(
                "dbo.Tourneys",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Name = c.String(),
                        StartDt = c.DateTime(nullable: false),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.TourneyTeams",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Status = c.Byte(nullable: false),
                        TourneyId = c.Int(nullable: false),
                        TeamId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Tourneys", t => t.TourneyId)
                .ForeignKey("dbo.Teams", t => t.TeamId)
                .Index(t => t.TourneyId)
                .Index(t => t.TeamId);
            
            AddColumn("dbo.Users", "Status", c => c.Byte(nullable: false));
            AddColumn("dbo.Users", "Role", c => c.Byte(nullable: false));
            AddColumn("dbo.Users", "Gender", c => c.Byte());
            AddColumn("dbo.Users", "FirstName", c => c.String());
            AddColumn("dbo.Users", "LastName", c => c.String());
            AddColumn("dbo.Users", "Password", c => c.Binary());
            AddColumn("dbo.Users", "Salt", c => c.Binary());
            AddColumn("dbo.Users", "Image", c => c.Guid());
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.TourneyTeams", "TeamId", "dbo.Teams");
            DropForeignKey("dbo.Players", "Team_Id", "dbo.Teams");
            DropForeignKey("dbo.LeagueTeams", "TeamId", "dbo.Teams");
            DropForeignKey("dbo.Matches", "HomeId", "dbo.Teams");
            DropForeignKey("dbo.Matches", "GuestId", "dbo.Teams");
            DropForeignKey("dbo.TourneyTeams", "TourneyId", "dbo.Tourneys");
            DropForeignKey("dbo.Matches", "TourneyId", "dbo.Tourneys");
            DropForeignKey("dbo.LeagueTeams", "LeagueId", "dbo.Leagues");
            DropForeignKey("dbo.Matches", "LeagueId", "dbo.Leagues");
            DropForeignKey("dbo.Goals", "MatchId", "dbo.Matches");
            DropForeignKey("dbo.Goals", "TeamId", "dbo.Teams");
            DropForeignKey("dbo.Goals", "AuthorId", "dbo.Players");
            DropForeignKey("dbo.Goals", "AssistantId", "dbo.Players");
            DropForeignKey("dbo.Posts", "UserId", "dbo.Users");
            DropForeignKey("dbo.Comments", "UserId", "dbo.Users");
            DropForeignKey("dbo.PostItems", "PostId", "dbo.Posts");
            DropForeignKey("dbo.Comments", "PostId", "dbo.Posts");
            DropIndex("dbo.TourneyTeams", new[] { "TeamId" });
            DropIndex("dbo.TourneyTeams", new[] { "TourneyId" });
            DropIndex("dbo.LeagueTeams", new[] { "TeamId" });
            DropIndex("dbo.LeagueTeams", new[] { "LeagueId" });
            DropIndex("dbo.Matches", new[] { "LeagueId" });
            DropIndex("dbo.Matches", new[] { "TourneyId" });
            DropIndex("dbo.Matches", new[] { "GuestId" });
            DropIndex("dbo.Matches", new[] { "HomeId" });
            DropIndex("dbo.Players", new[] { "Team_Id" });
            DropIndex("dbo.Goals", new[] { "AssistantId" });
            DropIndex("dbo.Goals", new[] { "TeamId" });
            DropIndex("dbo.Goals", new[] { "AuthorId" });
            DropIndex("dbo.Goals", new[] { "MatchId" });
            DropIndex("dbo.PostItems", new[] { "PostId" });
            DropIndex("dbo.Posts", new[] { "UserId" });
            DropIndex("dbo.Comments", new[] { "PostId" });
            DropIndex("dbo.Comments", new[] { "UserId" });
            DropColumn("dbo.Users", "Image");
            DropColumn("dbo.Users", "Salt");
            DropColumn("dbo.Users", "Password");
            DropColumn("dbo.Users", "LastName");
            DropColumn("dbo.Users", "FirstName");
            DropColumn("dbo.Users", "Gender");
            DropColumn("dbo.Users", "Role");
            DropColumn("dbo.Users", "Status");
            DropTable("dbo.TourneyTeams");
            DropTable("dbo.Tourneys");
            DropTable("dbo.LeagueTeams");
            DropTable("dbo.Leagues");
            DropTable("dbo.Matches");
            DropTable("dbo.Teams");
            DropTable("dbo.Players");
            DropTable("dbo.Goals");
            DropTable("dbo.PostItems");
            DropTable("dbo.Posts");
            DropTable("dbo.Comments");
        }
    }
}
