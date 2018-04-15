using System.Security.Cryptography;
using System.Text;

namespace FootballManagementApi.Helpers
{
	public static class PasswordHelper
	{
		public static byte[] HashPassword(string password, byte[] salt)
		{
			byte[] passwordBuff = Encoding.UTF8.GetBytes(password);
			byte[] passwordBuffSalt = ArrayHelper.ConcatinateArrays(passwordBuff, salt);

			using (SHA256 sha = SHA256.Create())
			{
				return sha.ComputeHash(passwordBuffSalt);
			}
		}

		public static byte[] GenerateSalt() => RandomHelper.NextBytes(new byte[64]);
	}
}
