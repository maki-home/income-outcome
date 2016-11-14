package am.ik.home.income;

import java.time.LocalDate;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import am.ik.home.IncomeOutcomeSink;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class IncomeEventHandler {
	private final IncomeRepository incomeRepository;

	public IncomeEventHandler(IncomeRepository incomeRepository) {
		this.incomeRepository = incomeRepository;
	}

	@StreamListener(IncomeOutcomeSink.INCOME_INPUT)
	public void handleIncome(Income income) {
		log.info("handle {}", income);
		if (income.getIncomeDate() == null) {
			income.setIncomeDate(LocalDate.now());
		}
		incomeRepository.save(income);
	}
}
