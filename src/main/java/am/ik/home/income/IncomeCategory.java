package am.ik.home.income;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
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
public class IncomeCategory implements Serializable {
	@Id
	@NotNull
	private Integer categoryId;
	@NotNull
	private String categoryName;
}
