using System.Runtime.Serialization;

namespace FootballManagementApi.Enums
{
	public enum BlogItemType : byte
	{
		[EnumMember(Value = "text")]
		Text = 0,

		[EnumMember(Value = "image")]
		Image = 1,

		[EnumMember(Value = "link")]
		Link = 2
	}
}
