using FootballManagementApi.DAL.Models.ComplexTypes;
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
			Players = new HashSet<MatchPlayer>();
			Shots = new HashSet<Shot>();
		}

		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }
		
		public int HomeId { get; set; }

		[ForeignKey(nameof(HomeId))]
		public virtual Team Home { get; set; }
		
		public DateTimeOffset? EndDt { get; set; }

		public int GuestId { get; set; }

		[ForeignKey(nameof(GuestId))]
		public virtual Team Guest { get; set; }

		public MatchStatus Status { get; set; }

		public DateTimeOffset StartDt { get; set; }

		public MatchData HomeData { get; set; } = new MatchData();

		public MatchData GuestData { get; set; } = new MatchData();

		public int TourneyId { get; set; }

		[ForeignKey(nameof(TourneyId))]
		public virtual Tourney Tourney { get; set; }
		
		public virtual ICollection<Goal> Goals { get; set; }

		public virtual ICollection<MatchPlayer> Players { get; set; }

		public virtual ICollection<Shot> Shots { get; set; }
	}
}
