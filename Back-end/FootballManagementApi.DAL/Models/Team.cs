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
			Leagues = new HashSet<LeagueTeam>();
		}

		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }

		public string Name { get; set; }

		public string City { get; set; }

		public string County { get; set; }

		public virtual ICollection<Player> Players { get; set; }

		public virtual ICollection<TourneyTeam> Tourneys { get; set; }

		public virtual ICollection<LeagueTeam> Leagues { get; set; }
	}
}
