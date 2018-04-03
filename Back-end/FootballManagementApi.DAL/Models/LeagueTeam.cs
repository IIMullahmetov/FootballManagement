using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FootballManagementApi.DAL.Models
{
	public class LeagueTeam : IEntity
	{
		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }

		public int Score { get; set; }

		public int LeagueId { get; set; }

		[ForeignKey(nameof(LeagueId))]
		public virtual League League { get; set; }

		public int TeamId { get; set; }

		[ForeignKey(nameof(TeamId))]
		public virtual Team Team { get; set; }
	}
}
