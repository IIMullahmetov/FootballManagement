using FootballManagementApi.Enums;
using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FootballManagementApi.DAL.Models
{
	public class BlogItem : IEntity
	{
		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }

		public BlogItemType Type { get; set; }

		public Guid? Guid { get; set; }

		public string Link { get; set; }

		public string LinkText { get; set; }

		public string Text { get; set; }

		public int BlogId { get; set; }

		[ForeignKey(nameof(BlogId))]
		public virtual Blog Blog{ get; set; }
	}
}
