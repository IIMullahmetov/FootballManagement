using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Web.Configuration;

namespace FootballManagementApi.Helpers
{
    public static class GuidHelper
    {
        public static string GenerateGuid(string userName)
        {
            Regex regex = new Regex(@"\s");
            string path = WebConfigurationManager.AppSettings["rootFileStorage"];
            userName = regex.Replace(userName, "");
            for (int i = 0; i < userName.Length; i++)
            {
                path = path + userName[i] + "/";

                if (!Directory.Exists(path))
                    Directory.CreateDirectory(path);
            }
            return path;
        }
    }
}
