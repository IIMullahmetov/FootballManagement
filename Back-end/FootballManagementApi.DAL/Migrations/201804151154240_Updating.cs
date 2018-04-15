namespace FootballManagementApi.DAL.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class Updating : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Goals", "GoalDt", c => c.DateTimeOffset(nullable: false, precision: 7));
            AddColumn("dbo.Players", "Number", c => c.Int(nullable: false));
            AddColumn("dbo.Teams", "Logotype", c => c.Guid(nullable: false));
        }
        
        public override void Down()
        {
            DropColumn("dbo.Teams", "Logotype");
            DropColumn("dbo.Players", "Number");
            DropColumn("dbo.Goals", "GoalDt");
        }
    }
}
