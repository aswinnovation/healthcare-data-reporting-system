package com.healthcare.service;

import com.healthcare.dto.EncounterStatusSummaryResponse;
import com.healthcare.dto.EncounterTypeSummaryResponse;
import com.healthcare.dto.PatientEncounterCountResponse;
import com.healthcare.repository.PatientRepository;
import com.healthcare.repository.ReportRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * All Phase 4 reporting logic. Purely read-only: no report here ever
 * writes to PATIENT or ENCOUNTER.
 */
@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final PatientRepository patientRepository;

    public ReportService(ReportRepository reportRepository, PatientRepository patientRepository) {
        this.reportRepository = reportRepository;
        this.patientRepository = patientRepository;
    }

    public List<EncounterStatusSummaryResponse> getEncounterStatusSummary() {
        return reportRepository.countEncountersByStatus()
                .stream()
                .map(row -> new EncounterStatusSummaryResponse((String) row[0], toLong(row[1])))
                .toList();
    }

    public List<EncounterTypeSummaryResponse> getEncounterTypeSummary() {
        return reportRepository.countEncountersByType()
                .stream()
                .map(row -> new EncounterTypeSummaryResponse((String) row[0], toLong(row[1])))
                .toList();
    }

    public PatientEncounterCountResponse getEncounterCountForPatient(Long patientId) {
        // Fail fast with a clear error if the patient doesn't exist, rather
        // than silently reporting a count of 0 for an invalid ID.
        if (!patientRepository.existsById(patientId)) {
            throw new EntityNotFoundException("Patient not found with id: " + patientId);
        }

        Long count = reportRepository.countEncountersForPatientViaProcedure(patientId);
        return new PatientEncounterCountResponse(patientId, count);
    }

    private Long toLong(Object value) {
        return ((Number) value).longValue();
    }
}