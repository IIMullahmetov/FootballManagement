using System.Runtime.Serialization;

namespace FootballManagementApi.Enums
{
    public enum RegistrationStatus : byte
    {
        [EnumMember(Value = "accepted")]
        Accepted = 0,

        [EnumMember(Value = "pending")]
        Pending = 1
    }
}
