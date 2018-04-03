using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FootballManagementApi.DAL.Models
{
	public class Tourney : IEntity
	{
		public Tourney()
		{
			Matches = new HashSet<Match>();
			Teams = new HashSet<TourneyTeam>();
		}

		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }

		public string Name { get; set; }

		public DateTime StartDt { get; set; }
		
		public virtual ICollection<Match> Matches { get; set; }

		public virtual ICollection<TourneyTeam> Teams { get; set; }
	}
}
