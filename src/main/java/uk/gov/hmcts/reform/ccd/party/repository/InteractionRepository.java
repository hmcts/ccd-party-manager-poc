package uk.gov.hmcts.reform.ccd.party.repository;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import uk.gov.hmcts.reform.ccd.party.model.Interaction;

import java.util.List;

public interface InteractionRepository {

    @SqlQuery("select (select case when represented_by is not null then represented_by else party_name end "
        + " from party p where p.id = interaction_by) as party, * from interaction where ccd_reference_id = :caseId")
    @RegisterConstructorMapper(Interaction.class)
    List<Interaction> findByCaseId(String caseId);

    @SqlUpdate("insert into interaction (ccd_reference_id, interaction_by, interaction_type, description, "
        + "parent_id, interaction_date) values (:ccdReferenceId, :interactionBy, :interactionType, :description, "
        + ":parentId, now() at time zone 'UTC')")
    @GetGeneratedKeys("id")
    int createInteraction(@BindBean Interaction interaction);
}
