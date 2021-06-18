package uk.gov.hmcts.reform.ccd.party.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbcx.JdbcConnectionPool;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.reform.ccd.party.model.Interaction;
import uk.gov.hmcts.reform.ccd.party.service.InteractionService;

@Api
@Slf4j
@RestController
@RequestMapping(
    path = "/api",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE
)
public class ApiController {

    InteractionService interactionService;

    {
        JdbcConnectionPool dataSource = JdbcConnectionPool.create("jdbc:h2:mem:testdb", "sa", "password");
        interactionService = new InteractionService(dataSource);
    }

    @PostMapping(path = "/{caseId}")
    @ApiOperation("Store interactions")
    public String storeInteraction(
        @PathVariable("caseId") String caseId,
        @RequestBody Interaction interaction
    ) {
        try {
            interactionService.createInteraction(interaction);
        } catch (Exception e){
            log.error("failed due to: {}", e.getMessage());

            return "failed";
        }

        return "success";
    }

    @GetMapping(path = "/{caseId}")
    @ApiOperation("Get interactions for case id")
    public Interaction getInteractions(@PathVariable("caseId") String caseId) {
        Interaction interaction;
        try {
            interaction = interactionService.getInteraction(caseId);
        } catch (Exception e) {
            interaction = Interaction.builder().build();
        }

        return interaction;
    }
}
