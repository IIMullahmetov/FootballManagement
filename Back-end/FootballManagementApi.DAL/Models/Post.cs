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
		}

		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }

		public string Title { get; set; }

		public string Body { get; set; }

		public DateTimeOffset CreateDt { get; set; }

		public PostStatus Status { get; set; }

		public virtual ICollection<Comment> Comments { get; set; }
	}
}
