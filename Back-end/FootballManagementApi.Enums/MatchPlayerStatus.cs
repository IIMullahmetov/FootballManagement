using System.Runtime.Serialization;

namespace FootballManagementApi.Enums
{
	public enum MatchPlayerStatus : byte
	{
		[EnumMember(Value = "main")]
		Main = 0,

		[EnumMember(Value = "spare")]
		Spare = 1,

		[EnumMember(Value = "excluded")]
		Excluded = 2
	}
}
