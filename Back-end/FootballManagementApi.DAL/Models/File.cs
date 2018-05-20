using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FootballManagementApi.DAL.Models
{
	public class File : IEntity
	{
		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }

		public string Name { get; set; }

		public int Size { get; set; }

		public DateTimeOffset CreateDt { get; set; }

		public Guid Guid { get; set; }

		public int UserId { get; set; }

		[ForeignKey(nameof(UserId))]
		public virtual User User { get; set; }
	}
}
