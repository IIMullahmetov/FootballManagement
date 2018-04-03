using System.Runtime.Serialization;

namespace FootballManagementApi.Enums
{
	public enum Role : byte
	{
		[EnumMember(Value = "admin")]
		Admin = 0,

		[EnumMember(Value = "user")]
		User = 1
	}
}
