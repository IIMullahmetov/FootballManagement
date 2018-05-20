﻿using System.Runtime.Serialization;

namespace FootballManagementApi.Enums
{
	public enum MatchStatus : byte
	{
		[EnumMember(Value = "pending")]
		Pending = 0,

		[EnumMember(Value = "started")]
		Started = 1,

		[EnumMember(Value = "finished")]
		Finished = 2,
	}
}
