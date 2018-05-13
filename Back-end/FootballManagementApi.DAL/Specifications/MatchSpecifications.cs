using FootballManagementApi.DAL.Models;
using FootballManagementApi.Enums;

namespace FootballManagementApi.DAL.MatchSpecifications
{
	public class StatusSpecification : Specification<Match>
	{
		public StatusSpecification(MatchStatus? status)
		{
			if (status != null)
			{
				Predicate = m => m.Status == status;
			}
			else
			{
				Predicate = m => true;
			}
		}
	}
}
