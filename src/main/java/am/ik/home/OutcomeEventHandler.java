package am.ik.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RepositoryEventHandler(Outcome.class)
public class OutcomeEventHandler {
    @Autowired
    Member member;

    @HandleBeforeCreate
    public void onBeforeCreate(Outcome outcome) {
        if (outcome.getOutcomeBy() == null) {
            outcome.setOutcomeBy(member.userId());
        }
        if (outcome.getOutcomeDate() == null) {
            outcome.setOutcomeDate(LocalDate.now());
        }
    }
}
