using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;

namespace FootballManagementApi.DAL
{
	public class SelectOptions<TEntity> where TEntity : class, IEntity
	{
		public int Take { get; set; }

		public int Skip { get; set; }

		public Func<IQueryable<TEntity>, IOrderedQueryable<TEntity>> OrderBy { get; set; }

		private List<Expression<Func<TEntity, object>>> _includes;

		public List<Expression<Func<TEntity, object>>> GetIncludes() => _includes;

		private void SetIncludes(List<Expression<Func<TEntity, object>>> value) => _includes = value;

		public SelectOptions() => SetIncludes(new List<Expression<Func<TEntity, object>>>());
	}
}
