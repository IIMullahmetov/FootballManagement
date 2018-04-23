using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using FootballManagementApi.Enums;

namespace FootballManagementApi.DAL.Models
{
    public class Registration : IEntity
    {
        [Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        public DateTimeOffset CreateDt { get; set; }

        public Guid Guid { get; set; }

        public RegistrationStatus Status { get; set; }

        public virtual User User { get; set; }
    }
}
