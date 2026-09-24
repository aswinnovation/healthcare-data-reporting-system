package com.healthcare.dto;

import java.time.LocalDateTime;

/**
 * Inbound shape for creating/updating an encounter.
 *
 * Takes patientId rather than a Patient object - the service layer will be
 * responsible for looking up the Patient entity from this ID. No Bean
 * Validation annotations yet, consistent with PatientRequest.
 */
public class EncounterRequest {

    private Long patientId;
    private LocalDateTime encounterDate;
    private String encounterType;
    private String diagnosis;
    private String status;
    private String notes;

    public EncounterRequest() {
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getEncounterDate() {
        return encounterDate;
    }

    public void setEncounterDate(LocalDateTime encounterDate) {
        this.encounterDate = encounterDate;
    }

    public String getEncounterType() {
        return encounterType;
    }

    public void setEncounterType(String encounterType) {
        this.encounterType = encounterType;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}