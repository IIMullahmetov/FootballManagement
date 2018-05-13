using FootballManagementApi.DAL.Models;
using FootballManagementApi.Enums;

namespace FootballManagementApi.DAL.Specifications.Comments
{
	public class StatusSpecification : Specification<Comment>
	{
		public StatusSpecification(CommentStatus status) => Predicate = c => c.Status == status;
	}
}
