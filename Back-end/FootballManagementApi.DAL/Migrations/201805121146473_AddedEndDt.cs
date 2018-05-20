namespace FootballManagementApi.DAL.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AddedEndDt : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.MatchPlayers",
                c => new
                    {
                        Match_Id = c.Int(nullable: false),
                        Player_Id = c.Int(nullable: false),
                    })
                .PrimaryKey(t => new { t.Match_Id, t.Player_Id })
                .ForeignKey("dbo.Matches", t => t.Match_Id, cascadeDelete: true)
                .ForeignKey("dbo.Players", t => t.Player_Id, cascadeDelete: true)
                .Index(t => t.Match_Id)
                .Index(t => t.Player_Id);
            
            AddColumn("dbo.Matches", "EndDt", c => c.DateTimeOffset(precision: 7));
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.MatchPlayers", "Player_Id", "dbo.Players");
            DropForeignKey("dbo.MatchPlayers", "Match_Id", "dbo.Matches");
            DropIndex("dbo.MatchPlayers", new[] { "Player_Id" });
            DropIndex("dbo.MatchPlayers", new[] { "Match_Id" });
            DropColumn("dbo.Matches", "EndDt");
            DropTable("dbo.MatchPlayers");
        }
    }
}
