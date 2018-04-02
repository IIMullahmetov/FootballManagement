using System.Runtime.Serialization;

namespace FootballManagementApi.DAL.Enums
{
	public enum Gender : byte
	{
		[EnumMember(Value = "woman")]
		Woman = 0,

		[EnumMember(Value = "man")]
		Man = 1,

		[EnumMember(Value = "nobody")]
		Nobody = 2
	}
}
