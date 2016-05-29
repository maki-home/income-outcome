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
    @Autowired
    OutcomeCategoryTrainer trainer;

    @HandleBeforeCreate
    public void onBeforeCreate(Outcome outcome) {
        if (outcome.getOutcomeBy() == null) {
            outcome.setOutcomeBy(member.userId());
        }
        if (outcome.getOutcomeDate() == null) {
            outcome.setOutcomeDate(LocalDate.now());
        }
    }

    @HandleAfterCreate
    public void onAfterCreate(Outcome outcome) {
        trainer.train(outcome.getOutcomeName(), outcome.getOutcomeCategory().getCategoryId());
    }
}
