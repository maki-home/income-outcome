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
        System.out.println("before create " + outcome);
    }

    @HandleAfterCreate
    public void onAfterCreate(Outcome outcome) {
        System.out.println("after create " + outcome);
    }

    @HandleBeforeSave
    public void onBeforeSave(Outcome outcome) {
        System.out.println("before save " + outcome);
    }

    @HandleAfterSave
    public void onAfterSave(Outcome outcome) {
        System.out.println("after save " + outcome);
    }
}
