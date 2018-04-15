using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FootballManagementApi.DAL.Models
{
	public class League : IEntity
	{
		public League()
		{
			Matches = new HashSet<Match>();
			Teams = new HashSet<LeagueTeam>();
		}

		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }

		public string Name { get; set; }

		public DateTime StartDt { get; set; }

		public virtual ICollection<Match> Matches { get; set; }

		public virtual ICollection<LeagueTeam> Teams { get; set; }
	}
}
