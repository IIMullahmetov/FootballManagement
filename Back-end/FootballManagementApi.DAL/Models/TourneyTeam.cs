using FootballManagementApi.Enums;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FootballManagementApi.DAL.Models
{
	public class TourneyTeam : IEntity
	{
		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }

		public TourneyTeamStatus Status { get; set; }

		public int TourneyId { get; set; }
		 
		[ForeignKey(nameof(TourneyId))]
		public virtual Tourney Tourney { get; set; }

		public int TeamId { get; set; }

		[ForeignKey(nameof(TeamId))]
		public virtual Team Team { get; set; }
	}
}
