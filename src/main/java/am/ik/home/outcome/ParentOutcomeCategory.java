package am.ik.home.outcome;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
public class ParentOutcomeCategory implements Serializable {
    @Id
    @NotNull
    private Integer parentCategoryId;
    @NotNull
    private String parentCategoryName;
}
