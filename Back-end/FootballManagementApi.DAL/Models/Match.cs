using FootballManagementApi.Enums;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FootballManagementApi.DAL.Models
{
	public class Match : IEntity
	{
		public Match()
		{
			Goals = new HashSet<Goal>();
		}

		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }
		
		public int HomeId { get; set; }

		[ForeignKey(nameof(HomeId))]
		public virtual Team Home { get; set; }
		
		public int GuestId { get; set; }

		[ForeignKey(nameof(GuestId))]
		public virtual Team Guest { get; set; }

		public MatchStatus Status { get; set; }

		public DateTimeOffset StartDt { get; set; }

		public int? TourneyId { get; set; }

		[ForeignKey(nameof(TourneyId))]
		public virtual Tourney Tourney { get; set; }

		public int? LeagueId { get; set; }

		[ForeignKey(nameof(LeagueId))]
		public virtual League League { get; set; }

		public virtual ICollection<Goal> Goals { get; set; }
	}
}
