package am.ik.home.outcome;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
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
	private boolean isCreditCard;

	// Audit
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date createdAt;
	@CreatedBy
	@Column(updatable = false)
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

		boolean isCreditCard();
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
