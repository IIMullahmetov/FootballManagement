namespace FootballManagementApi.DAL.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AddedBlog : DbMigration
    {
        public override void Up()
        {
            DropIndex("dbo.Comments", new[] { "PostId" });
            CreateTable(
                "dbo.Blogs",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Title = c.String(),
                        CreateDt = c.DateTimeOffset(nullable: false, precision: 7),
                        UserId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Users", t => t.UserId)
                .Index(t => t.UserId);
            
            CreateTable(
                "dbo.BlogItems",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Type = c.Byte(nullable: false),
                        Guid = c.Guid(),
                        Link = c.String(),
                        LinkText = c.String(),
                        Text = c.String(),
                        BlogId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Blogs", t => t.BlogId)
                .Index(t => t.BlogId);
            
            CreateTable(
                "dbo.BlogDislikes",
                c => new
                    {
                        Blog_Id = c.Int(nullable: false),
                        User_Id = c.Int(nullable: false),
                    })
                .PrimaryKey(t => new { t.Blog_Id, t.User_Id })
                .ForeignKey("dbo.Blogs", t => t.Blog_Id, cascadeDelete: true)
                .ForeignKey("dbo.Users", t => t.User_Id, cascadeDelete: true)
                .Index(t => t.Blog_Id)
                .Index(t => t.User_Id);
            
            CreateTable(
                "dbo.BlogLikes",
                c => new
                    {
                        Blog_Id = c.Int(nullable: false),
                        User_Id = c.Int(nullable: false),
                    })
                .PrimaryKey(t => new { t.Blog_Id, t.User_Id })
                .ForeignKey("dbo.Blogs", t => t.Blog_Id, cascadeDelete: true)
                .ForeignKey("dbo.Users", t => t.User_Id, cascadeDelete: true)
                .Index(t => t.Blog_Id)
                .Index(t => t.User_Id);
            
            AddColumn("dbo.Comments", "BlogId", c => c.Int());
            AlterColumn("dbo.Comments", "PostId", c => c.Int());
            CreateIndex("dbo.Comments", "PostId");
            CreateIndex("dbo.Comments", "BlogId");
            AddForeignKey("dbo.Comments", "BlogId", "dbo.Blogs", "Id");
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.BlogLikes", "User_Id", "dbo.Users");
            DropForeignKey("dbo.BlogLikes", "Blog_Id", "dbo.Blogs");
            DropForeignKey("dbo.BlogItems", "BlogId", "dbo.Blogs");
            DropForeignKey("dbo.BlogDislikes", "User_Id", "dbo.Users");
            DropForeignKey("dbo.BlogDislikes", "Blog_Id", "dbo.Blogs");
            DropForeignKey("dbo.Blogs", "UserId", "dbo.Users");
            DropForeignKey("dbo.Comments", "BlogId", "dbo.Blogs");
            DropIndex("dbo.BlogLikes", new[] { "User_Id" });
            DropIndex("dbo.BlogLikes", new[] { "Blog_Id" });
            DropIndex("dbo.BlogDislikes", new[] { "User_Id" });
            DropIndex("dbo.BlogDislikes", new[] { "Blog_Id" });
            DropIndex("dbo.BlogItems", new[] { "BlogId" });
            DropIndex("dbo.Blogs", new[] { "UserId" });
            DropIndex("dbo.Comments", new[] { "BlogId" });
            DropIndex("dbo.Comments", new[] { "PostId" });
            AlterColumn("dbo.Comments", "PostId", c => c.Int(nullable: false));
            DropColumn("dbo.Comments", "BlogId");
            DropTable("dbo.BlogLikes");
            DropTable("dbo.BlogDislikes");
            DropTable("dbo.BlogItems");
            DropTable("dbo.Blogs");
            CreateIndex("dbo.Comments", "PostId");
        }
    }
}
