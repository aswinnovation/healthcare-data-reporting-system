package com.healthcare.controller;

import com.healthcare.dto.EncounterStatusSummaryResponse;
import com.healthcare.dto.EncounterTypeSummaryResponse;
import com.healthcare.dto.PatientEncounterCountResponse;
import com.healthcare.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Thin, read-only REST layer for reporting. Every endpoint here is a GET -
 * this module never creates, updates, or deletes Patient/Encounter data.
 */
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/encounters/status-summary")
    public ResponseEntity<List<EncounterStatusSummaryResponse>> getEncounterStatusSummary() {
        return ResponseEntity.ok(reportService.getEncounterStatusSummary());
    }

    @GetMapping("/encounters/type-summary")
    public ResponseEntity<List<EncounterTypeSummaryResponse>> getEncounterTypeSummary() {
        return ResponseEntity.ok(reportService.getEncounterTypeSummary());
    }

    @GetMapping("/patients/{patientId}/encounter-count")
    public ResponseEntity<PatientEncounterCountResponse> getEncounterCountForPatient(
            @PathVariable("patientId") Long patientId) {
        return ResponseEntity.ok(reportService.getEncounterCountForPatient(patientId));
    }
}