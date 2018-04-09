using FootballManagementApi.DAL.Models;
using System.Linq;

namespace FootballManagementApi.DAL.Specifications.Posts
{
	public class TitleSpecification : Specification<Post>
	{
		public TitleSpecification(string searchString)
		{
			if (searchString != null)
			{
				EvalPredicate = p => p.Title.Contains(searchString);
			}
			else
			{
				EvalPredicate = p => true;
			}
		}
	}

	public class ContentSpecification : Specification<Post>
	{
		public ContentSpecification(string searchString)
		{
			if (searchString != null)
			{
				EvalPredicate = p => p.Items.Any(i => i.Type == Enums.PostItemType.Text && i.Text.Contains(searchString));
			}
			else
			{
				EvalPredicate = p => true;
			}
		}
	}

	public class StatusSpecification : Specification<Post>
	{
		public StatusSpecification(Enums.PostStatus status = Enums.PostStatus.Published)
			=> EvalPredicate = p => p.Status == status;
	}
}
