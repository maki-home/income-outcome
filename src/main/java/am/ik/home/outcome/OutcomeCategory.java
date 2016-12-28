package am.ik.home.outcome;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutcomeCategory implements Serializable {
	@Id
	@NotNull
	private Integer categoryId;
	@NotNull
	private String categoryName;
	@ManyToOne
	@JoinColumn(name = "parent_category_id")
	@NotNull
	private ParentOutcomeCategory parentOutcomeCategory;
}
