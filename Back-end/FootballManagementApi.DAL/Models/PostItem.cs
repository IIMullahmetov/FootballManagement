using FootballManagementApi.Enums;
using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FootballManagementApi.DAL.Models
{
	public class PostItem : IEntity
	{
		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }

		public PostItemType Type { get; set; }

		public Guid? Guid { get; set; }

		public string Link { get; set; }

		public string LinkText { get; set; }

		public string Text { get; set; }

		public int PostId { get; set; }

		[ForeignKey(nameof(PostId))]
		public virtual Post Post { get; set; }
	}
}
