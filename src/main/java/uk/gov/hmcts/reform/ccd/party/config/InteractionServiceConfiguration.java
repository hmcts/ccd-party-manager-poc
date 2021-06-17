package uk.gov.hmcts.reform.ccd.party.config;

import org.springframework.context.annotation.Bean;
import uk.gov.hmcts.reform.ccd.party.service.InteractionService;

import javax.sql.DataSource;

public class InteractionServiceConfiguration {

    @Bean
    public InteractionService getInteractionService(DataSource dataSource) {
        return new InteractionService(dataSource);
    }
}
