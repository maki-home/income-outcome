package am.ik.home;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.rest.core.config.Projection;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class Outcome implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long outcomeId;
    @NotNull
    private String outcomeName;
    @NotNull
    @Min(0)
    private Long amount;
    @NotNull
    @Min(0)
    private Integer quantity;
    @NotNull
    private LocalDate outcomeDate;
    @NotNull
    private String outcomeBy;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull
    private OutcomeCategory outcomeCategory;

    // Audit
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @LastModifiedBy
    private String updatedBy;

    public static interface InlineCategory {
        Long getOutcomeId();

        String getOutcomeName();

        Long getAmount();

        Integer getQuantity();

        LocalDate getOutcomeDate();

        String getOutcomeBy();

        OutcomeCategory getOutcomeCategory();
    }

    public static interface SummaryByDate {
        LocalDate getOutcomeDate();

        Long getSubTotal();
    }

    public static interface SummaryByParentCategory {
        Integer getParentCategoryId();

        String getParentCategoryName();

        Long getSubTotal();
    }
}
