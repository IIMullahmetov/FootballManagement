using System;

namespace FootballManagementApi.DAL
{
	public abstract class Specification<TEntity> : ISpecification<TEntity>
	{
		public bool IsSatisfiedBy(TEntity entity) => throw new NotImplementedException();
	}

	internal class AndSpecification<TEntity> : ISpecification<TEntity>
	{
		private readonly ISpecification<TEntity> _spec1;
		private readonly ISpecification<TEntity> _spec2;

		protected ISpecification<TEntity> Spec1 => _spec1;

		protected ISpecification<TEntity> Spec2 => _spec2;

		internal AndSpecification(ISpecification<TEntity> spec1, ISpecification<TEntity> spec2)
		{
			if (spec1 == null)
			{
				throw new ArgumentNullException("spec1");
			}

			if (spec2 == null)
			{
				throw new ArgumentNullException("spec2");
			}

			_spec1 = spec1;
			_spec2 = spec2;
		}

		public bool IsSatisfiedBy(TEntity candidate)
		{
			return Spec1.IsSatisfiedBy(candidate) && Spec2.IsSatisfiedBy(candidate);
		}
	}

	internal class OrSpecification<TEntity> : ISpecification<TEntity>
	{
		private readonly ISpecification<TEntity> _spec1;
		private readonly ISpecification<TEntity> _spec2;

		protected ISpecification<TEntity> Spec1 => _spec1;

		protected ISpecification<TEntity> Spec2 => _spec2;

		internal OrSpecification(ISpecification<TEntity> spec1, ISpecification<TEntity> spec2)
		{
			if (spec1 == null)
			{
				throw new ArgumentNullException("spec1");
			}

			if (spec2 == null)
			{
				throw new ArgumentNullException("spec2");
			}

			_spec1 = spec1;
			_spec2 = spec2;
		}

		public bool IsSatisfiedBy(TEntity candidate)
		{
			return Spec1.IsSatisfiedBy(candidate) || Spec2.IsSatisfiedBy(candidate);
		}
	}

	internal class NotSpecification<TEntity> : ISpecification<TEntity>
	{
		private readonly ISpecification<TEntity> _wrapped;

		protected ISpecification<TEntity> Wrapped => _wrapped;

		internal NotSpecification(ISpecification<TEntity> spec)
		{
			if (spec == null)
			{
				throw new ArgumentNullException("spec");
			}

			_wrapped = spec;
		}

		public bool IsSatisfiedBy(TEntity candidate)
		{
			return !Wrapped.IsSatisfiedBy(candidate);
		}
	}

	public static class ExtensionMethods
	{
		public static ISpecification<TEntity> And<TEntity>(this ISpecification<TEntity> spec1, ISpecification<TEntity> spec2)
		{
			return new AndSpecification<TEntity>(spec1, spec2);
		}

		public static ISpecification<TEntity> Or<TEntity>(this ISpecification<TEntity> spec1, ISpecification<TEntity> spec2)
		{
			return new OrSpecification<TEntity>(spec1, spec2);
		}

		public static ISpecification<TEntity> Not<TEntity>(this ISpecification<TEntity> spec)
		{
			return new NotSpecification<TEntity>(spec);
		}
	}
}
