package am.ik.home;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface OutcomeRepository extends CrudRepository<Outcome, Long> {
    @Query("SELECT x FROM Outcome x WHERE x.outcomeDate = :outcomeDate")
    List<Outcome> findByOutcomeDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Param("outcomeDate") LocalDate outcomeDate);
}
