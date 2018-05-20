using System.Runtime.Serialization;

namespace FootballManagementApi.Enums
{
	public enum ShotType : byte
	{
		/// <summary>
		/// По воротам
		/// </summary>
		[EnumMember(Value = "goal")]
		Goal = 0,

		/// <summary>
		/// В створ
		/// </summary>
		[EnumMember(Value = "alignment")]
		Alignment = 1,

		/// <summary>
		/// Угловой
		/// </summary>
		[EnumMember(Value = "corner")]
		Corner = 2,

		/// <summary>
		/// Оффсайд
		/// </summary>
		[EnumMember(Value = "offside")]
		Offside = 3
	}
}
