package uk.gov.hmcts.reform.ccd.party.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Parties {

    @JsonProperty("case_id")
    private final String ccdReferenceId;

    @JsonProperty("parties")
    private final List<Party> parties;
}
