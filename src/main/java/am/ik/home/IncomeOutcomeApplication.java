package am.ik.home;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
@EnableResourceServer
@EnableJpaAuditing
public class IncomeOutcomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(IncomeOutcomeApplication.class, args);
    }

    @Profile("!cloud")
    @Bean
    RequestDumperFilter requestDumperFilter() {
        return new RequestDumperFilter();
    }

    @Bean
    InitializingBean init(OutcomeRepository outcomeRepository) {
        return () -> {
            outcomeRepository.save(Arrays.asList(
                    Outcome.builder().outcomeName("にんじん").amount(100L).outcomeDate(LocalDate.now()).outcomeBy("00000000-0000-0000-0000-000000000000").build(),
                    Outcome.builder().outcomeName("ビール").amount(250L).outcomeDate(LocalDate.now()).outcomeBy("00000000-0000-0000-0000-000000000000").build(),
                    Outcome.builder().outcomeName("えのき").amount(50L).outcomeDate(LocalDate.now()).outcomeBy("00000000-0000-0000-0000-000000000000").build()
            ));
        };
    }

    @Bean
    AuditorAware<String> auditorProvider(Member member) {
        return member::userId;
    }
}
