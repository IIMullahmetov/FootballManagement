using System.Runtime.Serialization;

namespace FootballManagementApi.Enums
{
	public enum Gender : byte
	{
		[EnumMember(Value = "woman")]
		Woman = 0,

		[EnumMember(Value = "man")]
		Man = 1,
	}
}
