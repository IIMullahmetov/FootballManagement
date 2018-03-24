using System.Runtime.Serialization;

namespace FootballManagementApi.DAL.Enums
{
	public enum UserState : byte
	{
		[EnumMember(Value = "active")]
		Active = 0,

		[EnumMember(Value = "blocked")]
		Blocked = 1,

		[EnumMember(Value = "pending")]
		Pending = 2
	}
}
