package uk.gov.hmcts.reform.ccd.party.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.reform.ccd.party.exception.ApiException;
import uk.gov.hmcts.reform.ccd.party.model.Interaction;
import uk.gov.hmcts.reform.ccd.party.model.Parties;
import uk.gov.hmcts.reform.ccd.party.model.Party;
import uk.gov.hmcts.reform.ccd.party.service.InteractionService;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@Api
@Slf4j
@RestController
@RequestMapping(
    path = "/party-manager-parties",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE
)
public class ApiController {

    @Autowired
    private InteractionService interactionService;

    @Autowired
    private ObjectMapper mapper;

    @PostMapping(path = "")
    @ApiOperation("Create party information")
    public ResponseEntity<String> createPartyInformation(
        @RequestBody Parties parties
    ) {
        try {
            int updated = interactionService.createPartyInformation(parties);
            log.info("createPartyInformation response: success ({})", updated);
        } catch (Exception e) {
            log.error("failed due to: {}", e.getMessage());
            throw new ApiException("Unable to store parties for case id: " + parties.getCcdReferenceId());
        }

        return ResponseEntity
            .status(HttpStatus.OK)
            .body("success");
    }

    @GetMapping(path = "/{caseId}")
    @ApiOperation("Get parties for case id")
    public ResponseEntity<Parties> getPartyInformation(@PathVariable("caseId") String caseId) {
        Parties parties;
        try {
            List<Party> partyList = interactionService.getParties(caseId);
            parties = Parties.builder().ccdReferenceId(caseId).parties(partyList).build();
            log.info("getPartyInformation response: {}",
                     mapper.writerWithDefaultPrettyPrinter().writeValueAsString(parties));
        } catch (Exception e) {
            log.error("failed due to: {}", e.getMessage());
            throw new ApiException("Unable to get parties for case id: " + caseId);
        }

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(parties);
    }

    @PostMapping(path = "/interaction")
    @ApiOperation("Store interaction")
    public ResponseEntity<String> addPartyInteraction(
        @RequestBody Interaction interaction
    ) {
        try {
            int updated = interactionService.createInteraction(interaction);
            log.info("addPartyInteraction response: success ({})", updated);
        } catch (Exception e) {
            log.error("failed due to: {}", e.getMessage());
            throw new ApiException("Unable to store interactions for case id: " + interaction.getCcdReferenceId());
        }

        return ResponseEntity
            .status(HttpStatus.OK)
            .body("success");
    }

    @GetMapping(path = "/interactions/{caseId}")
    @ApiOperation("Get interactions for case id")
    public ResponseEntity<List<Interaction>> getPartyInteractionInformation(@PathVariable("caseId") String caseId) {
        List<Interaction> interactions;
        try {
            interactions = interactionService.getInteractions(caseId);
            log.info("getInteractions response: {}",
                     mapper.writerWithDefaultPrettyPrinter().writeValueAsString(interactions));
        } catch (Exception e) {
            log.error("failed due to: {}", e.getMessage());
            throw new ApiException("Unable to get interactions for case id: " + caseId);
        }

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(interactions);
    }

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<String> handleApiException(ApiException e) {
        log.error("An error occured when accessing the Party Manager API", e.getMessage());
        return status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
