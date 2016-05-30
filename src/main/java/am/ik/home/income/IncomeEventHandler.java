package am.ik.home.income;

import am.ik.home.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Component
@RepositoryEventHandler(Income.class)
public class IncomeEventHandler {
    @Autowired
    Member member;

    @HandleBeforeCreate
    public void onBeforeCreate(Income Income) {
        if (StringUtils.isEmpty(Income.getIncomeBy())) {
            Income.setIncomeBy(member.userId());
        }
        if (Income.getIncomeDate() == null) {
            Income.setIncomeDate(LocalDate.now());
        }
    }
}
