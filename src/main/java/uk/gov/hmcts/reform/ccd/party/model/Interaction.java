package uk.gov.hmcts.reform.ccd.party.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Interaction {

    private final String id;

    @JsonProperty("party")
    private final String party;

    @JsonProperty("case_id")
    private final String ccdReferenceId;

    @JsonProperty("initiated_by")
    private final int interactionBy;

    @JsonProperty("interaction_type")
    private final String interactionType;

    @JsonProperty("interaction_date")
    private final LocalDateTime interactionDate;

    @JsonProperty("respondents")
    private final String respondents;

    @JsonProperty("description")
    private final String description;

    @JsonProperty("parent_id")
    private final int parentId;
}
