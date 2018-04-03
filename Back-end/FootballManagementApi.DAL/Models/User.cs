using FootballManagementApi.Enums;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FootballManagementApi.DAL.Models
{
	public class User : IEntity
	{
		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }

		public UserStatus Status { get; set; }

		public Role Role { get; set; }

		public Gender Gender { get; set; } = Gender.Nobody;

		public string FirstName { get; set; }

		public string LastName { get; set; }

		public byte[] Password { get; set; }

		public byte[] Hash { get; set; }


	}
}
