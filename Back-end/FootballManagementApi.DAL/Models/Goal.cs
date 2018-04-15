using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FootballManagementApi.DAL.Models
{
	public class Goal : IEntity
	{
		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }

		public DateTimeOffset GoalDt { get; set; }

		public int MatchId { get; set; }

		[ForeignKey(nameof(MatchId))]
		public virtual Match Match { get; set; }

		public int AuthorId { get; set; }

		[ForeignKey(nameof(AuthorId))]
		public virtual Player Author { get; set; }

		public int TeamId { get; set; }

		[ForeignKey(nameof(TeamId))]
		public virtual Team Team { get; set; }

		public int? AssistantId { get; set; }

		[ForeignKey(nameof(AssistantId))]
		public virtual Player Assistant { get; set; }
	}
}
