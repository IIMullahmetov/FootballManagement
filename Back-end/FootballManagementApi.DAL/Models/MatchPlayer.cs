using FootballManagementApi.Enums;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FootballManagementApi.DAL.Models
{
	public class MatchPlayer : IEntity
	{
		public MatchPlayer()
		{
			Cards = new HashSet<Card>();
		}

		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }

		public MatchPlayerStatus Status { get; set; }

		public int MatchId { get; set; }

		[ForeignKey(nameof(MatchId))]
		public virtual Match Match { get; set; }

		public int PlayerId { get; set; }

		[ForeignKey(nameof(PlayerId))]
		public virtual Player Player { get; set; }

		public virtual ICollection<Card> Cards { get; set; }
	}
}
