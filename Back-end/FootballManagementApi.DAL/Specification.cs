using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;

namespace FootballManagementApi.DAL
{
	public class Specification<TEntity> : ISpecification<TEntity>
	{
		public Expression<Func<TEntity, bool>> Predicate { get; protected set; }

		protected Specification() { }

		public Specification(Expression<Func<TEntity, bool>> predicate) => Predicate = predicate;

		public IQueryable<TEntity> SatisfyingItemsFrom(IQueryable<TEntity> query)
		{
			return query.Where(Predicate);
		}

		public ISpecification<TEntity> And(ISpecification<TEntity> other) => new AndSpecification<TEntity>(this, other);
		
		public ISpecification<TEntity> Or(ISpecification<TEntity> other) => new OrSpecification<TEntity>(this, other);
	}

	public class AndSpecification<TEntity> : Specification<TEntity>
	{
		private readonly ISpecification<TEntity> _left;
		private readonly ISpecification<TEntity> _right;

		public AndSpecification(ISpecification<TEntity> left, ISpecification<TEntity> right)
		{
			_left = left;
			_right = right;

			Predicate = left.Predicate.And(right.Predicate);
		}
	}

	public class OrSpecification<TEntity> : Specification<TEntity>
	{
		private readonly ISpecification<TEntity> _left;
		private readonly ISpecification<TEntity> _right;

		public OrSpecification(ISpecification<TEntity> left, ISpecification<TEntity> right)
		{
			_left = left;
			_right = right;

			Predicate = left.Predicate.Or(right.Predicate);
		}
	}

	public class ParameterRebinder : ExpressionVisitor
	{
		private readonly Dictionary<ParameterExpression, ParameterExpression> _map;

		public ParameterRebinder(Dictionary<ParameterExpression, ParameterExpression> map)
			=> _map = map ?? new Dictionary<ParameterExpression, ParameterExpression>();

		public static Expression ReplaceParameters(Dictionary<ParameterExpression, ParameterExpression> map, Expression exp) => new ParameterRebinder(map).Visit(exp);

		protected override Expression VisitParameter(ParameterExpression p)
		{
			if (_map.TryGetValue(p, out ParameterExpression replacement))
			{
				p = replacement;
			}

			return base.VisitParameter(p);
		}
	}

	public static class ExpressionExtensions
	{
		public static Expression<TEntity> Compose<TEntity>(this Expression<TEntity> left, Expression<TEntity> right,
			Func<Expression, Expression, Expression> merge)
		{
			Dictionary<ParameterExpression, ParameterExpression> map = left.Parameters
				.Select((f, i) => new { f, s = right.Parameters[i] })
				.ToDictionary(p => p.s, p => p.f);

			Expression secondBody = ParameterRebinder.ReplaceParameters(map, right.Body);
			return Expression.Lambda<TEntity>(merge(left.Body, secondBody), left.Parameters);
		}

		public static Expression<Func<TEntity, bool>> And<TEntity>(this Expression<Func<TEntity, bool>> left,
			Expression<Func<TEntity, bool>> second) => left.Compose(second, Expression.And);

		public static Expression<Func<TEntity, bool>> Or<TEntity>(this Expression<Func<TEntity, bool>> left,
			Expression<Func<TEntity, bool>> second) => left.Compose(second, Expression.Or);
	}
}
