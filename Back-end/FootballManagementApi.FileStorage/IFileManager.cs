using System.Threading.Tasks;

namespace FootballManagementApi.FileStorage
{
	public interface IFileManager
	{
		Task WriteFileAsync(byte[] file, string path);

		Task<byte[]> GetFileAsync(string path, int size);
	}
}
