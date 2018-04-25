namespace FootballManagementApi.DAL.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class SomeUpdating : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.RefreshTokens",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        CreateDt = c.DateTimeOffset(nullable: false, precision: 7),
                        Guid = c.Guid(nullable: false),
                        ExpireAt = c.DateTimeOffset(nullable: false, precision: 7),
                        UserId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Users", t => t.UserId, cascadeDelete: true)
                .Index(t => t.UserId);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.RefreshTokens", "UserId", "dbo.Users");
            DropIndex("dbo.RefreshTokens", new[] { "UserId" });
            DropTable("dbo.RefreshTokens");
        }
    }
}
