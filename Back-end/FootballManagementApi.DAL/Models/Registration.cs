using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using FootballManagementApi.Enums;

namespace FootballManagementApi.DAL.Models
{
    public class Registration : IEntity
    {
        [Key, DatabaseGenerated(DatabaseGeneratedOption.None)]
        public int Id { get; set; }

        [ForeignKey(nameof(Id))]
        public virtual User User { get; set; }

        public DateTimeOffset CreateDt { get; set; }

        public Guid Guid { get; set; }

        public RegistrationStatus Status { get; set; }

        public RegistrationType Type { get; set; }
    }
}
