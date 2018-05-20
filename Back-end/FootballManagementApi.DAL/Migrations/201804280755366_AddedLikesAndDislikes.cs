namespace FootballManagementApi.DAL.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AddedLikesAndDislikes : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.PostDislikes",
                c => new
                    {
                        Post_Id = c.Int(nullable: false),
                        User_Id = c.Int(nullable: false),
                    })
                .PrimaryKey(t => new { t.Post_Id, t.User_Id })
                .ForeignKey("dbo.Posts", t => t.Post_Id, cascadeDelete: true)
                .ForeignKey("dbo.Users", t => t.User_Id, cascadeDelete: true)
                .Index(t => t.Post_Id)
                .Index(t => t.User_Id);
            
            CreateTable(
                "dbo.PostLikes",
                c => new
                    {
                        Post_Id = c.Int(nullable: false),
                        User_Id = c.Int(nullable: false),
                    })
                .PrimaryKey(t => new { t.Post_Id, t.User_Id })
                .ForeignKey("dbo.Posts", t => t.Post_Id, cascadeDelete: true)
                .ForeignKey("dbo.Users", t => t.User_Id, cascadeDelete: true)
                .Index(t => t.Post_Id)
                .Index(t => t.User_Id);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.PostLikes", "User_Id", "dbo.Users");
            DropForeignKey("dbo.PostLikes", "Post_Id", "dbo.Posts");
            DropForeignKey("dbo.PostDislikes", "User_Id", "dbo.Users");
            DropForeignKey("dbo.PostDislikes", "Post_Id", "dbo.Posts");
            DropIndex("dbo.PostLikes", new[] { "User_Id" });
            DropIndex("dbo.PostLikes", new[] { "Post_Id" });
            DropIndex("dbo.PostDislikes", new[] { "User_Id" });
            DropIndex("dbo.PostDislikes", new[] { "Post_Id" });
            DropTable("dbo.PostLikes");
            DropTable("dbo.PostDislikes");
        }
    }
}
