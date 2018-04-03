namespace FootballManagementApi.DAL
{
	public interface ISpecification<TEntity>
	{
		bool IsSatisfiedBy(TEntity entity);
	}
}