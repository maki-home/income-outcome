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

    @Query("SELECT x.outcomeDate AS outcomeDate, SUM(x.amount * x.quantity) AS subTotal FROM Outcome x WHERE x.outcomeDate BETWEEN :fromDate AND :toDate GROUP BY x.outcomeDate ORDER BY x.outcomeDate ASC")
    List<Outcome.SummaryByDate> findSummaryByDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Param("fromDate") LocalDate fromDate,
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Param("toDate") LocalDate toDate);

    @Query("SELECT x.outcomeCategory.parentOutcomeCategory.parentCategoryName AS parentCategoryName, x.outcomeCategory.parentOutcomeCategory.parentCategoryId AS parentCategoryId, SUM(x.amount) AS subTotal FROM Outcome x WHERE x.outcomeDate BETWEEN :fromDate AND :toDate GROUP BY x.outcomeCategory.parentOutcomeCategory.parentCategoryId ORDER BY x.outcomeCategory.parentOutcomeCategory.parentCategoryId ASC")
    List<Outcome.SummaryByParentCategory> findSummaryByParentCategory(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Param("fromDate") LocalDate fromDate,
                                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Param("toDate") LocalDate toDate);
}
