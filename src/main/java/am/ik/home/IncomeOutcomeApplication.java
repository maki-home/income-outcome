package am.ik.home;

import am.ik.home.income.Income;
import am.ik.home.income.IncomeCategory;
import am.ik.home.income.IncomeRepository;
import am.ik.home.outcome.Outcome;
import am.ik.home.outcome.OutcomeCategory;
import am.ik.home.outcome.OutcomeRepository;
import am.ik.home.outcome.ParentOutcomeCategory;
import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.AsyncRestTemplate;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
@EnableResourceServer
@EnableJpaAuditing
@EnableBinding(IncomeOutcomeSink.class)
public class IncomeOutcomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(IncomeOutcomeApplication.class, args);
    }

    @Profile("!cloud")
    @Bean
    RequestDumperFilter requestDumperFilter() {
        return new RequestDumperFilter();
    }

    @Profile("!cloud")
    @Bean
    InitializingBean init(OutcomeRepository outcomeRepository, IncomeRepository incomeRepository) {
        return () -> {
            outcomeRepository.save(Arrays.asList(
                    Outcome.builder().outcomeName("にんじん")
                            .amount(100L)
                            .quantity(1)
                            .isCreditCard(true)
                            .outcomeDate(LocalDate.now())
                            .outcomeCategory(OutcomeCategory.builder().categoryId(6).build())
                            .outcomeBy("00000000-0000-0000-0000-000000000000").build(),
                    Outcome.builder()
                            .outcomeName("ビール")
                            .amount(250L)
                            .quantity(1)
                            .isCreditCard(true)
                            .outcomeDate(LocalDate.now())
                            .outcomeCategory(OutcomeCategory.builder().categoryId(13).build())
                            .outcomeBy("00000000-0000-0000-0000-000000000000").build(),
                    Outcome.builder()
                            .outcomeName("えのき")
                            .amount(50L)
                            .quantity(1)
                            .isCreditCard(true)
                            .outcomeDate(LocalDate.now())
                            .outcomeCategory(OutcomeCategory.builder().categoryId(6).build())
                            .outcomeBy("00000000-0000-0000-0000-000000000000").build(),
                    Outcome.builder()
                            .outcomeName("チャージ")
                            .amount(1000L)
                            .quantity(1)
                            .isCreditCard(false)
                            .outcomeDate(LocalDate.now().minusDays(1))
                            .outcomeCategory(OutcomeCategory.builder().categoryId(45).build())
                            .outcomeBy("00000000-0000-0000-0000-000000000000").build(),
                    Outcome.builder()
                            .outcomeName("クリーニング")
                            .amount(3000L)
                            .quantity(1)
                            .isCreditCard(false)
                            .outcomeDate(LocalDate.now().minusDays(2))
                            .outcomeCategory(OutcomeCategory.builder().categoryId(21).build())
                            .outcomeBy("00000000-0000-0000-0000-000000000000").build()
            ));
            incomeRepository.deleteAll();
            incomeRepository.save(Arrays.asList(
                    Income.builder()
                            .incomeName("6月給料")
                            .incomeDate(LocalDate.now().withMonth(6))
                            .incomeCategory(IncomeCategory.builder().categoryId(1).build())
                            .amount(200000L)
                            .incomeBy("00000000-0000-0000-0000-000000000000")
                            .build(),
                    Income.builder()
                            .incomeName("6月ボーナス")
                            .incomeDate(LocalDate.now().withMonth(6))
                            .incomeCategory(IncomeCategory.builder().categoryId(2).build())
                            .amount(400000L)
                            .incomeBy("00000000-0000-0000-0000-000000000000")
                            .build(),
                    Income.builder()
                            .incomeName("5月給料")
                            .incomeDate(LocalDate.now().withMonth(5))
                            .incomeCategory(IncomeCategory.builder().categoryId(1).build())
                            .amount(200000L)
                            .incomeBy("00000000-0000-0000-0000-000000000000")
                            .build(),
                    Income.builder()
                            .incomeName("4月給料")
                            .incomeDate(LocalDate.now().withMonth(4))
                            .incomeCategory(IncomeCategory.builder().categoryId(1).build())
                            .amount(200000L)
                            .incomeBy("00000000-0000-0000-0000-000000000000")
                            .build()
            ));
        };
    }


    @Configuration
    static class RestMvcConfig extends RepositoryRestConfigurerAdapter {
        @Override
        public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
            config.exposeIdsFor(Outcome.class, OutcomeCategory.class, ParentOutcomeCategory.class, Income.class, IncomeCategory.class);
        }
    }

    @Bean
    AuditorAware<String> auditorProvider(Member member) {
        return member::userId;
    }

    @Bean
    AsyncRestTemplate asyncRestTemplate() {
        return new AsyncRestTemplate();
    }
}
