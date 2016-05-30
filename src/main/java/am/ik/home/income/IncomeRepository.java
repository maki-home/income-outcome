package am.ik.home.income;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource(excerptProjection = Income.InlineCategory.class)
public interface IncomeRepository extends CrudRepository<Income, Long> {
    @Query("SELECT x FROM Income x JOIN FETCH x.incomeCategory WHERE x.incomeDate BETWEEN :fromDate AND :toDate ORDER BY x.incomeDate, x.incomeCategory.categoryId")
    List<Income> findByIncomeDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Param("fromDate") LocalDate fromDate,
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Param("toDate") LocalDate toDate);
}
