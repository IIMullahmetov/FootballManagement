using System.IO;
using System.Threading.Tasks;

namespace FootballManagementApi.FileStorage
{
    public class FileManager : IFileManager
    {
		public async Task<byte[]> GetFileAsync(string path, int size)
		{
			return File.ReadAllBytes(path);
		}
		
		public async Task WriteFileAsync(byte[] file, string path)
		{
			using (FileStream stream = new FileStream(path, FileMode.Create))
			{
				await stream.WriteAsync(file, 0, file.Length);
				stream.Flush();
				stream.Close();
			}
		}
	}
}
