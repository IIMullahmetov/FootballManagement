namespace FootballManagementApi.DAL.Migrations
{
    using System.Data.Entity.Migrations;
    
    public partial class UserIdIsRequiredInFile : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("dbo.Files", "UserId", "dbo.Users");
            DropIndex("dbo.Files", new[] { "UserId" });
            AlterColumn("dbo.Files", "UserId", c => c.Int(nullable: false));
            CreateIndex("dbo.Files", "UserId");
            AddForeignKey("dbo.Files", "UserId", "dbo.Users", "Id", cascadeDelete: true);
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Files", "UserId", "dbo.Users");
            DropIndex("dbo.Files", new[] { "UserId" });
            AlterColumn("dbo.Files", "UserId", c => c.Int());
            CreateIndex("dbo.Files", "UserId");
            AddForeignKey("dbo.Files", "UserId", "dbo.Users", "Id");
        }
    }
}
