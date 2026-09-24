package com.healthcare.dto;

import java.time.LocalDateTime;

/**
 * Outbound shape returned by the Encounter APIs.
 *
 * Exposes patientId (a plain Long) instead of the Patient entity itself -
 * this keeps the API response flat, avoids leaking JPA entity/proxy details
 * over JSON, and sidesteps lazy-loading serialization issues since
 * Encounter.patient is fetched LAZY.
 */
public class EncounterResponse {

    private Long encounterId;
    private Long patientId;
    private LocalDateTime encounterDate;
    private String encounterType;
    private String diagnosis;
    private String status;
    private String notes;
    private LocalDateTime createdAt;

    public EncounterResponse() {
    }

    public EncounterResponse(Long encounterId, Long patientId, LocalDateTime encounterDate,
                             String encounterType, String diagnosis, String status,
                             String notes, LocalDateTime createdAt) {
        this.encounterId = encounterId;
        this.patientId = patientId;
        this.encounterDate = encounterDate;
        this.encounterType = encounterType;
        this.diagnosis = diagnosis;
        this.status = status;
        this.notes = notes;
        this.createdAt = createdAt;
    }

    public Long getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(Long encounterId) {
        this.encounterId = encounterId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}