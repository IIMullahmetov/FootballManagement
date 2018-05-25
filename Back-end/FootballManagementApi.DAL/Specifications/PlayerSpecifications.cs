using FootballManagementApi.DAL.Models;
using System;

namespace FootballManagementApi.DAL.PlayerSpecifications
{
	public class TourneySpecification : Specification<Match>
	{
		public TourneySpecification(int? id)
		{
			if (id != null)
			{
				Predicate = p => p.TourneyId == id.Value;
			}
			else
			{
				Predicate = p => true;
			}
		}
	}

	public class SeasonSpecification : Specification<Match>
	{
		public SeasonSpecification(DateTime? season)
		{
			if (season != null)
			{
				Predicate = p => p.Tourney.StartDt.Year == season.Value.Year;
			}
			else
			{
				Predicate = p => true;
			}
		}
	}
}
