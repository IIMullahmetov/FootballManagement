using System.IO;
using System.Threading.Tasks;

namespace FootballManagementApi.FileStorage
{
    public class FileManager : IFileManager
    {
		public async Task<byte[]> GetFileAsync(string path, int size)
		{
			using (FileStream stream = new FileStream(path, FileMode.Open))
			{
				byte[] buffer = new byte[size];
				await stream.ReadAsync(buffer, 0, size);
				stream.Flush();
				stream.Close();
				return buffer;
			}
		}
		
		public async Task WriteFileAsync(byte[] file, string path)
		{
			using (FileStream stream = new FileStream(path, FileMode.CreateNew))
			{
				await stream.WriteAsync(file, 0, file.Length);
				stream.Flush();
				stream.Close();
			}
		}
	}
}
