namespace FootballManagementApi.DAL.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class DeletedIntroFromPost : DbMigration
    {
        public override void Up()
        {
            DropColumn("dbo.Posts", "Intro");
            DropColumn("dbo.Posts", "Image");
        }
        
        public override void Down()
        {
            AddColumn("dbo.Posts", "Image", c => c.Guid());
            AddColumn("dbo.Posts", "Intro", c => c.String());
        }
    }
}
