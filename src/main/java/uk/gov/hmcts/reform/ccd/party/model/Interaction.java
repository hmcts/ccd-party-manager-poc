package uk.gov.hmcts.reform.ccd.party.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Interaction {

    private final String id;
    private final String caseId;
    private final String interactionBy;
    private final String interactionType;
    private final LocalDate date;
    private final String description;
    private final String parentId;
}
