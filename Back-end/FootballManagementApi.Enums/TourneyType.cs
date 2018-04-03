using System.Runtime.Serialization;

namespace FootballManagementApi.Enums
{
	public enum TourneyType : byte
	{
		[EnumMember(Value = "internationalCup")]
		InternationalCup = 0,

		[EnumMember(Value = "cup")]
		Cup = 1,

		[EnumMember(Value = "championship")]
		Championship = 2,		
	}
}
