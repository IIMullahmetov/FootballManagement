using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;

namespace FootballManagementApi.DAL
{
	public abstract class GenericRepository<TEntity> : IGenericRepository<TEntity> where TEntity : class, IEntity
	{
		protected DbSet<TEntity> Entities { get; private set; }

		protected DbContext Context { get; private set; }

		public GenericRepository(DbContext context)
		{
			Context = context;
			Entities = context.Set<TEntity>();
		}

		public virtual Task<TEntity> SelectByIdAsync(int id) => Entities.FindAsync(id);

		public virtual Task<TEntity> SelectFirstOrDefaultAsync(Expression<Func<TEntity, bool>> predicate) => Entities.FirstOrDefaultAsync(predicate);

		public virtual Task<IEnumerable<TEntity>> SelectAsync(Expression<Func<TEntity, bool>> predicate) => Task.FromResult(Entities.Where(predicate).AsEnumerable());

		public virtual Task<int> CountAsync(Expression<Func<TEntity, bool>> predicate) => Entities.CountAsync(predicate);

		public virtual void Insert(TEntity entity) => Entities.Add(entity);
	}
}
