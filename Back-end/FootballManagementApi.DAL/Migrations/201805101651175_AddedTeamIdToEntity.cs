namespace FootballManagementApi.DAL.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AddedTeamIdToEntity : DbMigration
    {
        public override void Up()
        {
            RenameColumn(table: "dbo.Players", name: "Team_Id", newName: "TeamId");
            RenameIndex(table: "dbo.Players", name: "IX_Team_Id", newName: "IX_TeamId");
        }
        
        public override void Down()
        {
            RenameIndex(table: "dbo.Players", name: "IX_TeamId", newName: "IX_Team_Id");
            RenameColumn(table: "dbo.Players", name: "TeamId", newName: "Team_Id");
        }
    }
}
