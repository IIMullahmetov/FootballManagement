using FootballManagementApi.Enums;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FootballManagementApi.DAL.Models
{
	public class User : IEntity
	{
		public User()
		{
			Comments = new HashSet<Comment>();
		}

		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }

		public UserStatus Status { get; set; }

		public Role Role { get; set; }

		public Gender Gender { get; set; } = Gender.Nobody;

		public string FirstName { get; set; }

		public string LastName { get; set; }

		public byte[] Password { get; set; }

		public byte[] Salt { get; set; }

		public Guid Image { get; set; }

		public virtual ICollection<Comment> Comments { get; set; }
	}
}
