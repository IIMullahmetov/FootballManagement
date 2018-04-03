using System.Runtime.Serialization;

namespace FootballManagementApi.Enums
{
	public enum TourneyTeamStatus : byte
	{
		[EnumMember(Value = "participating")]
		Participating = 0,

		[EnumMember(Value = "out")]
		Out = 1
	}
}
