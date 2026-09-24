package com.healthcare.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

/**
 * Maps to the existing Oracle ENCOUNTER table.
 *
 * ddl-auto is "none" for this whole project, so this entity must match the
 * already-existing table exactly - it does not create or alter anything.
 */
@Entity
@Table(name = "encounter")
public class Encounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "encounter_id")
    private Long encounterId;

    // Many encounters belong to one patient. FetchType.LAZY so loading an
    // Encounter doesn't automatically pull the Patient unless it's actually
    // accessed - the patient relationship is read through this field, not
    // through a raw patient_id column, per the requirement to use @ManyToOne.
    // No cascade: deleting/saving an Encounter must never cascade into
    // Patient.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "encounter_date", nullable = false)
    private LocalDateTime encounterDate;

    @Column(name = "encounter_type", nullable = false, length = 50)
    private String encounterType;

    @Column(name = "diagnosis", length = 500)
    private String diagnosis;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "notes", length = 1000)
    private String notes;

    // created_at defaults on the Oracle side (CURRENT_TIMESTAMP), so it's
    // read-only from the JPA side, same pattern used for Patient.createdAt.
    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public Encounter() {
    }

    public Long getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(Long encounterId) {
        this.encounterId = encounterId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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