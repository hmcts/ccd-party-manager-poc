package uk.gov.hmcts.reform.ccd.party.service;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import uk.gov.hmcts.reform.ccd.party.model.Interaction;
import uk.gov.hmcts.reform.ccd.party.repository.InteractionRepository;

import java.util.Optional;
import javax.sql.DataSource;

public class InteractionService {

    private final Jdbi jdbi;

    public InteractionService(DataSource dataSource) {
        this.jdbi = Jdbi.create(dataSource)
            .installPlugin(new SqlObjectPlugin());
    }

    public Interaction getInteraction(String caseId) {
        Optional<Interaction> interaction =
            Optional.of(jdbi.withExtension(InteractionRepository.class, dao -> dao.findByCaseId(caseId)));

        return interaction.orElse(Interaction.builder().build());
    }

    public void createInteraction(Interaction interaction) {
        jdbi.useTransaction(handle -> {
            InteractionRepository dao = handle.attach(InteractionRepository.class);

            dao.createInteraction(interaction);
        });
    }
}
