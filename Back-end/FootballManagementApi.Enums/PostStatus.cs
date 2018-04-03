using System.Runtime.Serialization;

namespace FootballManagementApi.Enums
{
	public enum PostStatus : byte
	{
		[EnumMember(Value = "draft")]
		Draft = 0,

		[EnumMember(Value = "published")]
		Published = 1,

		[EnumMember(Value = "removed")]
		Removed = 2
	}
}
