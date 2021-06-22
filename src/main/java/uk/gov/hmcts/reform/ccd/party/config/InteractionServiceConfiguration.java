package uk.gov.hmcts.reform.ccd.party.config;

import org.h2.jdbcx.JdbcConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.hmcts.reform.ccd.party.service.InteractionService;

@Configuration
public class InteractionServiceConfiguration {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public InteractionService getInteractionService() {
        JdbcConnectionPool dataSource = JdbcConnectionPool.create(url, username, password);
        return new InteractionService(dataSource);
    }
}
