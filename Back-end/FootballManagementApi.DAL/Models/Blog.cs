using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FootballManagementApi.DAL.Models
{
	public class Blog : IEntity
	{
		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }

		public string Title { get; set; }

		public DateTimeOffset CreateDt { get; set; }

		public int UserId { get; set; }

		[ForeignKey(nameof(UserId))]
		public virtual User User { get; set; }

		public virtual ICollection<Comment> Comments { get; set; }

		public virtual ICollection<BlogItem> Items { get; set; }

		public virtual ICollection<User> Dislikes { get; set; }

		public virtual ICollection<User> Likes { get; set; }
	}
}
