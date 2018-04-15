using System.Linq;

namespace FootballManagementApi.Helpers
{
	public static class ArrayHelper
	{
		public static T[] ConcatinateArrays<T>(params T[][] arrays)
		{
			T[] result = new T[arrays.Sum(x => x.Length)];
			int i = 0;
			foreach (T[] array in arrays)
			{
				foreach (T item in array)
				{
					result[i++] = item;
				}
			}
			return result;
		}
	}
}
