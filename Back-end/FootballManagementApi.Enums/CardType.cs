using System.Runtime.Serialization;

namespace FootballManagementApi.Enums
{
	public enum CardType : byte
	{
		[EnumMember(Value = "yellow")]
		Yellow = 0,
		 
		[EnumMember(Value = "red")]
		Red = 1
	}
}
