using FootballManagementApi.DAL.Models;

namespace FootballManagementApi.DAL.BlogCommentSpecifications
{
	public class BlogSpecification : Specification<Comment>
	{
		public BlogSpecification(int id) => Predicate = b => b.BlogId == id;
	}
}
