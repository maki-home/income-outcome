package am.ik.home.income;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
