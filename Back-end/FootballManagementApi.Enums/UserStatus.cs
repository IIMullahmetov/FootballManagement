using System.Runtime.Serialization;

namespace FootballManagementApi.Enums
{
	public enum UserStatus : byte
	{
		[EnumMember(Value = "active")]
		Active = 0,

		[EnumMember(Value = "blocked")]
		Blocked = 1,

		[EnumMember(Value = "pending")]
		Pending = 2
	}
}
