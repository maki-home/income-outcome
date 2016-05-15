package am.ik.home;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource(excerptProjection = Outcome.InlineCategory.class)
public interface OutcomeRepository extends CrudRepository<Outcome, Long> {
    @Query("SELECT x FROM Outcome x JOIN FETCH x.outcomeCategory ORDER BY x.outcomeDate, x.outcomeCategory.categoryId")
    @Override
    Iterable<Outcome> findAll();

    @Query("SELECT x FROM Outcome x JOIN FETCH x.outcomeCategory WHERE x.outcomeDate = :outcomeDate ORDER BY x.outcomeCategory.categoryId")
    List<Outcome> findByOutcomeDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Param("outcomeDate") LocalDate outcomeDate);
}
