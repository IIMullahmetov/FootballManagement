using System.Runtime.Serialization;

namespace FootballManagementApi.Enums
{
	public enum CommentStatus
	{
		[EnumMember(Value = "active")]
		Active = 0,

		[EnumMember(Value = "removed")]
		Removed = 1
	}
}
