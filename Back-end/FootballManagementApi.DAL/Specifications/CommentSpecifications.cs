using FootballManagementApi.DAL.Models;
using FootballManagementApi.Enums;

namespace FootballManagementApi.DAL.Specifications.Comments
{
	public class StatusSpecification : Specification<Comment>
	{
		public StatusSpecification(CommentStatus status) => Predicate = c => c.Status == status;
	}

	public class PostSpecification : Specification<Comment>
	{
		public PostSpecification(int id) => Predicate = c => c.PostId == id;
	}
}
