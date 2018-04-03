using FootballManagementApi.Enums;
using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FootballManagementApi.DAL.Models
{
	public class Player : IEntity
	{
		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }

		public string FirstName { get; set; }

		public string LastName { get; set; }

		public DateTime BirthDt { get; set; }

		//TODO Пока хз для чего нужен, но должен понадобится
		public PlayerStatus Status { get; set; }

		public virtual Team Team { get; set; }
	}
}
