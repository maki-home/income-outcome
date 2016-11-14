package am.ik.home.outcome;

import java.time.LocalDate;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import am.ik.home.IncomeOutcomeSink;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OutcomeEventHandler {
	private final OutcomeRepository outcomeRepository;
	private final OutcomeCategoryTrainer trainer;

	public OutcomeEventHandler(OutcomeRepository outcomeRepository,
			OutcomeCategoryTrainer trainer) {
		this.outcomeRepository = outcomeRepository;
		this.trainer = trainer;
	}

	@StreamListener(IncomeOutcomeSink.OUTCOME_INPUT)
	public void handleOutcome(Outcome outcome) {
		log.info("handle {}", outcome);
		if (outcome.getOutcomeDate() == null) {
			outcome.setOutcomeDate(LocalDate.now());
		}
		outcomeRepository.save(outcome);
		trainer.train(outcome.getOutcomeName(),
				outcome.getOutcomeCategory().getCategoryId());
	}
}
