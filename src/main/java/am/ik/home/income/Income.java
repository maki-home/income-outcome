package am.ik.home.income;

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
public class Income implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long incomeId;
	@NotNull
	private String incomeName;
	@NotNull
	@Min(0)
	private Long amount;
	@NotNull
	private LocalDate incomeDate;
	@NotNull
	private String incomeBy;
	@ManyToOne
	@JoinColumn(name = "category_id")
	@NotNull
	private IncomeCategory incomeCategory;

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
		Long getIncomeId();

		String getIncomeName();

		Long getAmount();

		LocalDate getIncomeDate();

		String getIncomeBy();

		IncomeCategory getIncomeCategory();
	}
}
