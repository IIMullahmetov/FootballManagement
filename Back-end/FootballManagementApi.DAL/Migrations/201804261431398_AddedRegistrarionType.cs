namespace FootballManagementApi.DAL.Migrations
{
    using System.Data.Entity.Migrations;
    
    public partial class AddedRegistrarionType : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Users", "GoogleToken", c => c.String());
            AddColumn("dbo.Registrations", "Type", c => c.Byte(nullable: false));
        }
        
        public override void Down()
        {
            DropColumn("dbo.Registrations", "Type");
            DropColumn("dbo.Users", "GoogleToken");
        }
    }
} 