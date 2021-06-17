package uk.gov.hmcts.reform.ccd.party.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@Api
@Slf4j
@RestController
@RequestMapping(
    path = "/api",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE
)
public class ApiController {

    @PostMapping(path = "/{caseId}")
    @ApiOperation("Store interactions")
    public String storeInteraction(@PathVariable("caseId") String caseId) {
        return "";
    }

    @GetMapping(path = "/{caseId}")
    @ApiOperation("Get interactions for case id")
    public ResponseEntity<String> getInteractions(@PathVariable("caseId") String caseId) {
        return ok("Welcome to spring-boot-template");
    }
}
