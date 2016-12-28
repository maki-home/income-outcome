package am.ik.home.outcome;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OutcomeCategoryRepository
		extends CrudRepository<OutcomeCategory, Integer> {
	@Query("SELECT x FROM OutcomeCategory x JOIN FETCH x.parentOutcomeCategory ORDER BY x.categoryId")
	@Override
	Iterable<OutcomeCategory> findAll();
}
