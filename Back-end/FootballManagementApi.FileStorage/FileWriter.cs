using System.IO;


namespace FootballManagementApi.FileStorage
{
    public class FileWriter
    {
        public static async void WriteToFile(byte[] bytes, string path)
        {
            using (FileStream stream = File.Open(path, FileMode.Open))
            {
                await stream.WriteAsync(bytes, 0, bytes.Length);
            }
        }
    }
}
