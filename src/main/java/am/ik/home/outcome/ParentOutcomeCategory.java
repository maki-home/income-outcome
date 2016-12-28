package am.ik.home.outcome;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class ParentOutcomeCategory implements Serializable {
	@Id
	@NotNull
	private Integer parentCategoryId;
	@NotNull
	private String parentCategoryName;
}
