package uk.gov.hmcts.reform.ccd.party.repository;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import uk.gov.hmcts.reform.ccd.party.model.Interaction;

public interface InteractionRepository {

    @SqlQuery("select * from interaction where ccd_reference_id = :caseId")
    @RegisterConstructorMapper(Interaction.class)
    Interaction findByCaseId(String caseId);

    @SqlUpdate("insert into interaction (caseId, interactionBy, interactionType, date, description, parentId) "
        + "values (:ccd_reference_id, :interaction_by, :interaction_type, :interaction_date, :description, :parent_id)")
    void createInteraction(@BindBean Interaction interaction);
}
