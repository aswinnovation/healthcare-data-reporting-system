package com.healthcare.controller;

import com.healthcare.dto.EncounterRequest;
import com.healthcare.dto.EncounterResponse;
import com.healthcare.service.EncounterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/encounters")
public class EncounterController {

    private final EncounterService encounterService;

    public EncounterController(EncounterService encounterService) {
        this.encounterService = encounterService;
    }

    @PostMapping
    public ResponseEntity<EncounterResponse> createEncounter(@RequestBody EncounterRequest request) {
        EncounterResponse response = encounterService.createEncounter(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{encounterId}")
    public ResponseEntity<EncounterResponse> getEncounterById(@PathVariable("encounterId") Long encounterId) {
        return ResponseEntity.ok(encounterService.getEncounterById(encounterId));
    }

    @GetMapping
    public ResponseEntity<List<EncounterResponse>> getAllEncounters() {
        return ResponseEntity.ok(encounterService.getAllEncounters());
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<EncounterResponse>> getEncountersByPatientId(@PathVariable("patientId") Long patientId) {
        return ResponseEntity.ok(encounterService.getEncountersByPatientId(patientId));
    }

    @PutMapping("/{encounterId}")
    public ResponseEntity<EncounterResponse> updateEncounter(@PathVariable("encounterId") Long encounterId,
                                                             @RequestBody EncounterRequest request) {
        return ResponseEntity.ok(encounterService.updateEncounter(encounterId, request));
    }

    @DeleteMapping("/{encounterId}")
    public ResponseEntity<Void> deleteEncounter(@PathVariable("encounterId") Long encounterId) {
        encounterService.deleteEncounter(encounterId);
        return ResponseEntity.noContent().build();
    }
}