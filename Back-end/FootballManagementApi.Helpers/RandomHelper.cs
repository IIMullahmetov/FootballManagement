using System;
using System.Text;

namespace FootballManagementApi.Helpers
{
	public static class RandomHelper
	{
		private static object _lock = new object();
		private static Random Random { get; } = new Random();
		private static string String { get; } = "abcdefghiklmnopqrstvxyzABCDEFGHIKLMNOPQRSTVXYZ0123456789";

		public static int NextInt(int maxValue)
		{
			lock (_lock)
			{
				return Random.Next(maxValue: maxValue);
			}
		}

		public static int NextInt(int minValue, int maxValue)
		{
			lock (_lock)
			{
				return Random.Next(minValue: minValue, maxValue: maxValue);
			}
		}

		public static string NextString(int length)
		{
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < length; i++)
			{
				builder.Append(String[NextInt(String.Length)]);
			}
			return builder.ToString();
		}

		public static byte[] NextBytes(byte[] array)
		{
			lock (_lock)
			{
				Random.NextBytes(array);
				return array;
			}
		}
	}
}
