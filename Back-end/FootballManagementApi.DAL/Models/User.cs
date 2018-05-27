using FootballManagementApi.Enums;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FootballManagementApi.DAL.Models
{
	public class User : IEntity
	{
		public User()
		{
			Comments = new HashSet<Comment>();
			Posts = new HashSet<Post>();
			Files = new HashSet<File>();
            RefreshTokens = new HashSet<RefreshToken>();
            PostDislikes = new HashSet<Post>();
            PostLikes = new HashSet<Post>();
			BlogLikes = new HashSet<Blog>();
			BlogDislikes = new HashSet<Blog>();
			Blogs = new HashSet<Blog>();
		}

		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }

		public UserStatus Status { get; set; }

		public Role Role { get; set; }

		public Gender? Gender { get; set; }

		public string FirstName { get; set; }

		public string LastName { get; set; }

        public string Email { get; set; }

		public byte[] Password { get; set; }

		public byte[] Salt { get; set; }
        
		public Guid? Image { get; set; }

        public string GoogleToken { get; set; }

        public virtual Registration Registration { get; set; }

		public virtual ICollection<Comment> Comments { get; set; }

		public virtual ICollection<Post> Posts { get; set; }

		public virtual ICollection<File> Files { get; set; }

        public virtual ICollection<RefreshToken> RefreshTokens { get; set; }

        public virtual ICollection<Post> PostDislikes { get; set; }

        public virtual ICollection<Post> PostLikes { get; set; }

		public virtual ICollection<Blog> Blogs { get; set; }

		public virtual ICollection<Blog> BlogLikes { get; set; }

		public virtual ICollection<Blog> BlogDislikes { get; set; }
	}
}
