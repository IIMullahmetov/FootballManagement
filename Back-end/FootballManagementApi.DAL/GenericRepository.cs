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
		protected DbSet<TEntity> DbSet { get; private set; }

		public DbContext Context { get; set; }

		public GenericRepository(DbContext context)
		{
			Context = context;
			DbSet = context.Set<TEntity>();
		}

		public virtual Task<TEntity> SelectByIdAsync(int id, SelectOptions<TEntity> options = null)
		{
            if (options != null)
            {
                return SelectFirstOrDefaultAsync(e => e.Id == id, options);
            }
			return DbSet.FindAsync(id);
		}

		public Task<TEntity> SelectFirstOrDefaultAsync(Expression<Func<TEntity, bool>> predicate, SelectOptions<TEntity> options = null)
		{
			Specification<TEntity> specification = new Specification<TEntity>(predicate);
			return SelectFirstOrDefaultAsync(specification, options);
		}

		public Task<TEntity> SelectFirstOrDefaultAsync(ISpecification<TEntity> specification, SelectOptions<TEntity> options = null)
		{
			IQueryable<TEntity> query = DbSet;
			query = ApplySpecificationWithOptions(query, specification, options);
			return query.FirstOrDefaultAsync();
		}

		public Task<IEnumerable<TEntity>> SelectAsync(Expression<Func<TEntity, bool>> predicate, SelectOptions<TEntity> options = null)
		{
			Specification<TEntity> specification = new Specification<TEntity>(predicate);
			return SelectAsync(specification, options);
		}

		public async Task<IEnumerable<TEntity>> SelectAsync(ISpecification<TEntity> specification, SelectOptions<TEntity> options = null)
		{
			IQueryable<TEntity> query = DbSet;
			query = ApplySpecificationWithOptions(query, specification, options);
			return await query.ToListAsync();
		}

		public async Task<IEnumerable<TEntity>> SelectAllAsync(SelectOptions<TEntity> options = null)
		{
			IQueryable<TEntity> query = DbSet;
			query = ApplyOptions(query, options);
			return await query.ToListAsync();
		}

		public Task<bool> AnyAsync(Expression<Func<TEntity, bool>> predicate)
		{
			Specification<TEntity> specification = new Specification<TEntity>(predicate);
			return AnyAsync(specification);
		}

		public Task<bool> AnyAsync(ISpecification<TEntity> specification)
		{
			IQueryable<TEntity> query = DbSet;
			query = ApplySpecification(query, specification);
			return query.AnyAsync();
		}

		public Task<int> CountAsync(Expression<Func<TEntity, bool>> predicate)
		{
			Specification<TEntity> specification = new Specification<TEntity>(predicate);
			return CountAsync(specification);
		}

		public Task<int> CountAsync(ISpecification<TEntity> specification)
		{
			IQueryable<TEntity> query = DbSet;
			query = ApplySpecification(query, specification);
			return query.CountAsync();
		}

		public virtual void Insert(TEntity item)
		{
			DbSet.Add(item);
		}
		
		private IQueryable<TEntity> ApplySpecificationWithOptions(IQueryable<TEntity> query, ISpecification<TEntity> specification, SelectOptions<TEntity> options)
		{
			query = ApplySpecification(query, specification);
			query = ApplyOptions(query, options);
			return query;
		}

		private IQueryable<TEntity> ApplySpecification(IQueryable<TEntity> query, ISpecification<TEntity> specification)
		{
			query = specification.SatisfyingItemsFrom(query);
			return query;
		}

		private IQueryable<TEntity> ApplyOptions(IQueryable<TEntity> query, SelectOptions<TEntity> options)
		{
			if (options == null)
			{
				return query;
			}

			if (options.OrderBy != null)
			{
				query = options.OrderBy(query);
			}

			if (options.Skip > 0)
			{
				query = query.Skip(options.Skip);
			}

			if (options.Take > 0)
			{
				query = query.Take(options.Take);
			}

			if (options.Includes?.Count > 0)
			{
				query = options.Includes.Aggregate(query, (current, include) => current.Include(include));
			}

			return query;
		}
	}
}
