﻿using FootballManagementApi.Enums;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FootballManagementApi.DAL.Models
{
	public class Player : IEntity
	{
		public Player()
		{
			Goals = new HashSet<Goal>();
			Assists = new HashSet<Goal>();
			Matches = new HashSet<MatchPlayer>();
		}

		[Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int Id { get; set; }

		public string FirstName { get; set; }

		public string LastName { get; set; }

        public Guid Image { get; set; }

		public DateTime BirthDt { get; set; }

		public int Number { get; set; }

		//TODO Пока хз для чего нужен, но должен понадобится
		public PlayerStatus Status { get; set; }

		public int TeamId { get; set; }

		[ForeignKey(nameof(TeamId))]
		public virtual Team Team { get; set; }

		public virtual ICollection<Goal> Goals { get; set; }

		public virtual ICollection<Goal> Assists { get; set; }

		public virtual ICollection<MatchPlayer> Matches { get; set; }
	}
}
