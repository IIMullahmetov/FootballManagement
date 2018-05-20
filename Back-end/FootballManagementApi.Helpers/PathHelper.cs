using System;
using System.Configuration;
using System.IO;

namespace FootballManagementApi.Helpers
{
	public static class PathHelper
	{
		private static readonly string _fileRoot;

		static PathHelper()
		{
			_fileRoot = System.Web.Hosting.HostingEnvironment.MapPath("~") + ConfigurationManager.AppSettings["FileStorage"];
			if (!Directory.Exists(_fileRoot))
			{
				try
				{
					Directory.CreateDirectory(_fileRoot);
				}
				catch(Exception e)
				{

				}
			}
		}

		public static string GeneratePath(Guid guid)
		{
			return _fileRoot + guid;	
		}
	}
}
