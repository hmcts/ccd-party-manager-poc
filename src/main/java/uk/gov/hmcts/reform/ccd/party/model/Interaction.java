package uk.gov.hmcts.reform.ccd.party.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Interaction {

    private final String id;
    private final String ccdReferenceId;
    private final int interactionBy;
    private final String interactionType;
    private final LocalDateTime interactionDate;
    private final String description;
    private final int parentId;
}
