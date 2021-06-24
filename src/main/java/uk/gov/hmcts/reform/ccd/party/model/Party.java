package uk.gov.hmcts.reform.ccd.party.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Party {

    private final String id;

    @JsonProperty("name")
    private final String partyName;

    @JsonProperty("type")
    private final String partyCapacity;

    @JsonProperty("represented_by")
    private final String representedBy;

    @JsonIgnore
    private final String ccdReferenceId;

    public String getCcdReferenceId() {
        return ccdReferenceId;
    }

    public void setCcdReferenceId(String ccdReferenceId) {
        ccdReferenceId = ccdReferenceId;
    }
}
