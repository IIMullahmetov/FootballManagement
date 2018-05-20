using FootballManagementApi.Enums;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FootballManagementApi.DAL.Models
{
	public class Shot : IEntity
	{
		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }

		public ShotType Type { get; set; }

		public int MatchId { get; set; }

		[ForeignKey(nameof(MatchId))]
		public virtual Match Match { get; set; }

		public int TeamId { get; set; }

		[ForeignKey(nameof(TeamId))]
		public virtual Team Team { get; set; }
	}
}
