using System.Runtime.Serialization;

namespace FootballManagementApi.Enums
{
	public enum PlayerStatus : byte
	{
		[EnumMember(Value = "active")]
		Active = 0
	}
}
