using System.Runtime.Serialization;

namespace FootballManagementApi.Enums
{
    public enum RegistrationType : byte
    {
        [EnumMember(Value = "email")]
        Email = 0,

        [EnumMember(Value = "google")]
        Google = 1
    }
}
