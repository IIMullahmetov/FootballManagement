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
				EvalPredicate = m => m.Status == status;
			}
			else
			{
				EvalPredicate = m => true;
			}
		}
	}
}
