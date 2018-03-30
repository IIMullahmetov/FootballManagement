using System.Runtime.Serialization;

namespace FootballManagementApi.DAL.Enums
{
	public enum Role : byte
	{
		[EnumMember(Value = "admin")]
		Admin = 0,

		[EnumMember(Value = "user")]
		User = 1
	}
}
