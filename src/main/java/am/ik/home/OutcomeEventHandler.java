package am.ik.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

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
            outcome.setOutcomeDate(new Date());
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
