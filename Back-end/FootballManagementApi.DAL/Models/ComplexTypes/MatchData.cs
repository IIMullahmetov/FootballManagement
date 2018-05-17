using System.ComponentModel.DataAnnotations.Schema;

namespace FootballManagementApi.DAL.Models.ComplexTypes
{
	[ComplexType]
	public class MatchData
	{
		public double Possession { get; set; }

		public int Fouls { get; set; }
	}
}
