using System.Threading.Tasks;
using FootballManagementApi.DAL;
using FootballManagementApi.DAL.Models;
using FootballManagementApi.GlobalExceptionHandler.Exceptions;
using FootballManagementApi.Resources;

namespace FootballManagementApi.Services.Implementations
{
    public class PostServices : IPostServices
    {
        private IUnitOfWork _unitOfWork;

        public PostServices(IUnitOfWork unitOfWork)
        {
            _unitOfWork = unitOfWork;
        }

        public async Task DislikeAsync(User user, int id)
        {
            SelectOptions<Post> options = new SelectOptions<Post>();
            options.Includes.Add(p => p.Likes);
            options.Includes.Add(p => p.Dislikes);

            Post post = await _unitOfWork.GetPostRepository().SelectByIdAsync(id, options)
                ?? throw new ActionCannotBeExecutedException(ExceptionMessages.PostNotFound);

            if (post.Likes.Contains(user))
            {
                post.Likes.Remove(user);
                post.Dislikes.Add(user);
                return;
            }

            if (post.Dislikes.Contains(user))
            {
                post.Dislikes.Remove(user);
                return;
            }

            post.Dislikes.Add(user);
        }

        public async Task LikeAsync(User user, int id)
        {
            SelectOptions<Post> options = new SelectOptions<Post>();
            options.Includes.Add(p => p.Likes);
            options.Includes.Add(p => p.Dislikes);

            Post post = await _unitOfWork.GetPostRepository().SelectByIdAsync(id, options)
                ?? throw new ActionCannotBeExecutedException(ExceptionMessages.PostNotFound);

            if (post.Dislikes.Contains(user))
            {
                post.Dislikes.Remove(user);
                post.Likes.Add(user);
                return;
            }

            if (post.Likes.Contains(user))
            {
                post.Likes.Remove(user);
                return;
            }

            post.Likes.Add(user);
        }
    }
}
