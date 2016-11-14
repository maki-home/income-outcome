package am.ik.home;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface IncomeOutcomeSink {
	String INCOME_INPUT = "income-input";
	String OUTCOME_INPUT = "outcome-input";

	@Input(INCOME_INPUT)
	SubscribableChannel incomeInput();

	@Input(OUTCOME_INPUT)
	SubscribableChannel outcomeInput();
}
