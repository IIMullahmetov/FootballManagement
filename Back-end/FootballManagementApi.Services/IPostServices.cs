using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using FootballManagementApi.DAL.Models;

namespace FootballManagementApi.Services
{
    public interface IPostServices
    {
        Task LikeAsync(User user, int id);

        Task DislikeAsync(User user, int id);
    }
}
