package uk.gov.hmcts.reform.ccd.party.repository;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import uk.gov.hmcts.reform.ccd.party.model.Party;

import java.util.List;

public interface PartyRepository {

    @SqlQuery("select * from party where ccd_reference_id = :caseId")
    @RegisterConstructorMapper(Party.class)
    List<Party> findByCaseId(String caseId);

    @SqlUpdate("insert into party (ccd_reference_id, party_name, party_capacity, party_state, parent_id, "
        + "represented_by, last_updated) values (:caseId, :partyName, :partyCapacity, :partyState, "
        + ":parentId, :representedBy, now() at time zone 'UTC')")
    @GetGeneratedKeys("id")
    int createParty(@BindBean Party party, @Bind String caseId);
}
