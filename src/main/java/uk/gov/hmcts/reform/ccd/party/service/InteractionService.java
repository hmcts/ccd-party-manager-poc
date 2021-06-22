package uk.gov.hmcts.reform.ccd.party.service;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.ccd.party.model.Interaction;
import uk.gov.hmcts.reform.ccd.party.model.Parties;
import uk.gov.hmcts.reform.ccd.party.model.Party;
import uk.gov.hmcts.reform.ccd.party.repository.InteractionRepository;
import uk.gov.hmcts.reform.ccd.party.repository.PartyRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.sql.DataSource;

@Service
public class InteractionService {

    private final Jdbi jdbi;

    public InteractionService(DataSource dataSource) {
        this.jdbi = Jdbi.create(dataSource)
            .installPlugin(new SqlObjectPlugin());
    }

    public List<Interaction> getInteractions(String caseId) {
        return jdbi.withExtension(InteractionRepository.class, dao -> dao.findByCaseId(caseId));
    }

    public int createInteraction(Interaction interaction) {
        AtomicInteger id = new AtomicInteger();
        jdbi.useTransaction(handle -> {
            InteractionRepository dao = handle.attach(InteractionRepository.class);
            id.set(dao.createInteraction(interaction));
        });

        return id.get();
    }

    public List<Party> getParties(String caseId) {
        return jdbi.withExtension(PartyRepository.class, dao -> dao.findByCaseId(caseId));
    }

    public int createPartyInformation(Parties parties) {
        AtomicInteger id = new AtomicInteger();
        jdbi.useTransaction(handle -> {
            PartyRepository dao = handle.attach(PartyRepository.class);
            parties.getParties().forEach(party -> {
                if (dao.doesPartyExist(party.getId()) == 0) {
                    id.set(dao.createParty(party, parties.getCcdReferenceId()));
                } else {
                    id.set(dao.updateParty(party));
                }
            });
        });

        return id.get();
    }




}
