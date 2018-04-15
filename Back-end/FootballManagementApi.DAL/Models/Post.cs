using FootballManagementApi.Enums;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FootballManagementApi.DAL.Models
{
	public class Post : IEntity
	{
		public Post()
		{
			Comments = new HashSet<Comment>();
			Items = new HashSet<PostItem>();
		}

		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }

		public string Title { get; set; }

		public string Intro { get; set; }

		public Guid? Image { get; set; }

		public DateTimeOffset CreateDt { get; set; }

		public PostStatus Status { get; set; }

		public int UserId { get; set; }

		[ForeignKey(nameof(UserId))]
		public virtual User User { get; set; }

		public virtual ICollection<Comment> Comments { get; set; }

		public virtual ICollection<PostItem> Items { get; set; }
	}
}
