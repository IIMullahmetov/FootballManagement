namespace FootballManagementApi.DAL.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class ChangingLinkOfRegistrationAndUsers : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("dbo.Users", "Id", "dbo.Registrations");
            DropIndex("dbo.Users", new[] { "Id" });
            DropPrimaryKey("dbo.Registrations");
            AlterColumn("dbo.Registrations", "Id", c => c.Int(nullable: false));
            AddPrimaryKey("dbo.Registrations", "Id");
            CreateIndex("dbo.Registrations", "Id");
            AddForeignKey("dbo.Registrations", "Id", "dbo.Users", "Id");
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Registrations", "Id", "dbo.Users");
            DropIndex("dbo.Registrations", new[] { "Id" });
            DropPrimaryKey("dbo.Registrations");
            AlterColumn("dbo.Registrations", "Id", c => c.Int(nullable: false, identity: true));
            AddPrimaryKey("dbo.Registrations", "Id");
            CreateIndex("dbo.Users", "Id");
            AddForeignKey("dbo.Users", "Id", "dbo.Registrations", "Id");
        }
    }
}
