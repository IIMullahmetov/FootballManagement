using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq.Expressions;
using System.Threading.Tasks;

namespace FootballManagementApi.DAL
{
	public interface IGenericRepository<TEntity> where TEntity : class, IEntity
	{
		DbContext Context { get; set; }

		Task<TEntity> SelectByIdAsync(int id);

		Task<TEntity> SelectFirstOrDefaultAsync(Expression<Func<TEntity, bool>> predicate, SelectOptions<TEntity> options = null);

		Task<TEntity> SelectFirstOrDefaultAsync(ISpecification<TEntity> specification, SelectOptions<TEntity> options = null);

		Task<IEnumerable<TEntity>> SelectAsync(Expression<Func<TEntity, bool>> predicate, SelectOptions<TEntity> options = null);

		Task<IEnumerable<TEntity>> SelectAsync(ISpecification<TEntity> specification, SelectOptions<TEntity> options = null);

		Task<IEnumerable<TEntity>> SelectAllAsync(SelectOptions<TEntity> options = null);

		Task<bool> AnyAsync(Expression<Func<TEntity, bool>> predicate);

		Task<bool> AnyAsync(ISpecification<TEntity> specification);

		Task<int> CountAsync(Expression<Func<TEntity, bool>> predicate);

		Task<int> CountAsync(ISpecification<TEntity> specification);

		void Insert(TEntity item);
	}
}
