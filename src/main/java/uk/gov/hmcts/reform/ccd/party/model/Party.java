package uk.gov.hmcts.reform.ccd.party.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Party {

    private final String id;

    @JsonProperty("name")
    private final String partyName;

    @JsonProperty("type")
    private final String partyCapacity;

    @JsonProperty("state")
    private final String partyState;

    @JsonProperty("last_updated")
    private final LocalDateTime lastUpdated;

    @JsonProperty("represented_by")
    private final String representedBy;

    @JsonProperty("parent_id")
    private final int parentId;

    public String getCcdReferenceId() {
        return ccdReferenceId;
    }

    private final String ccdReferenceId;

    public void setCcdReferenceId(String ccdReferenceId) {
        ccdReferenceId = ccdReferenceId;
    }
}
