using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace FootballManagementApi.DAL
{
	public interface IGenericRepository<TEntity> where TEntity : IEntity
	{
		Task<TEntity> SelectByIdAsync(int id);

		Task<TEntity> SelectFirstOrDefaultAsync(Expression<Func<TEntity, bool>> predicate);

		//Task<TEntity> SelectFirstOrDefaultAsync();

		Task<IEnumerable<TEntity>> SelectAsync(Expression<Func<TEntity, bool>> predicate);

		//Task<IEnumerable<TEntity>> SelectAsync();

		Task<int> CountAsync(Expression<Func<TEntity, bool>> predicate);

		//Task<int> CountAsync();

		void Insert(TEntity entity);
	}
}
