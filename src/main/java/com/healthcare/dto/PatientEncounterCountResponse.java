package com.healthcare.dto;

/**
 * Response for GET /api/reports/patients/{patientId}/encounter-count.
 * Backed by the GET_PATIENT_ENCOUNTER_COUNT Oracle PL/SQL function.
 */
public class PatientEncounterCountResponse {

    private Long patientId;
    private Long encounterCount;

    public PatientEncounterCountResponse() {
    }

    public PatientEncounterCountResponse(Long patientId, Long encounterCount) {
        this.patientId = patientId;
        this.encounterCount = encounterCount;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getEncounterCount() {
        return encounterCount;
    }

    public void setEncounterCount(Long encounterCount) {
        this.encounterCount = encounterCount;
    }
}