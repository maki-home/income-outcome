package am.ik.home.income;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IncomeCategoryRepository
		extends CrudRepository<IncomeCategory, Integer> {
	@Query("SELECT x FROM IncomeCategory x ORDER BY x.categoryId")
	@Override
	Iterable<IncomeCategory> findAll();
}
