using FootballManagementApi.Enums;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FootballManagementApi.DAL.Models
{
	public class Comment : IEntity
	{
		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }

		public string Body { get; set; }

		public int Likes { get; set; }

		public int Dislikes { get; set; }

		public CommentStatus Status { get; set; }

		public int UserId { get; set; }

		[ForeignKey(nameof(UserId))]
		public virtual User User { get; set; }

		public int PostId { get; set; }

		[ForeignKey(nameof(PostId))]
		public virtual Post Post { get; set; }
	}
}
