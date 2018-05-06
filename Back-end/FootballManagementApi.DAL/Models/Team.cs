using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FootballManagementApi.DAL.Models
{
	public class Team : IEntity
	{
		public Team()
		{
			Players = new HashSet<Player>();
			Tourneys = new HashSet<TourneyTeam>();
			Goals = new HashSet<Goal>();
			HomeMathces = new HashSet<Match>();
			GuestMatches = new HashSet<Match>();
		}

		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }

		public string Name { get; set; }

		public string City { get; set; }

		public string County { get; set; }

		public Guid Logotype { get; set; }

		public virtual ICollection<Player> Players { get; set; }

		public virtual ICollection<TourneyTeam> Tourneys { get; set; }
		
		public virtual ICollection<Goal> Goals { get; set; }

		public virtual ICollection<Match> HomeMathces { get; set; }

		public virtual ICollection<Match> GuestMatches { get; set; }
	}
}
