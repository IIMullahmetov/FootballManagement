using System;
using System.Linq;
using System.Linq.Expressions;

namespace FootballManagementApi.DAL
{
	public interface ISpecification<TEntity>
	{
		Expression<Func<TEntity, bool>> Predicate { get; }

		IQueryable<TEntity> SatisfyingItemsFrom(IQueryable<TEntity> query);

		ISpecification<TEntity> And(ISpecification<TEntity> other);

		ISpecification<TEntity> Or(ISpecification<TEntity> other);
	}
}