package com.healthcare.service;

import com.healthcare.dto.EncounterRequest;
import com.healthcare.dto.EncounterResponse;
import com.healthcare.entity.Encounter;
import com.healthcare.entity.Patient;
import com.healthcare.repository.EncounterRepository;
import com.healthcare.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncounterService {

    private final EncounterRepository encounterRepository;
    private final PatientRepository patientRepository;

    public EncounterService(EncounterRepository encounterRepository, PatientRepository patientRepository) {
        this.encounterRepository = encounterRepository;
        this.patientRepository = patientRepository;
    }

    public EncounterResponse createEncounter(EncounterRequest request) {
        validateEncounterRequest(request);

        Patient patient = findPatientOrThrow(request.getPatientId());

        Encounter encounter = new Encounter();
        encounter.setPatient(patient);
        applyRequestToEntity(request, encounter);

        Encounter saved = encounterRepository.save(encounter);
        return toResponse(saved);
    }

    public EncounterResponse getEncounterById(Long encounterId) {
        Encounter encounter = findEncounterOrThrow(encounterId);
        return toResponse(encounter);
    }

    public List<EncounterResponse> getAllEncounters() {
        return encounterRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<EncounterResponse> getEncountersByPatientId(Long patientId) {
        return encounterRepository.findByPatient_PatientId(patientId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public EncounterResponse updateEncounter(Long encounterId, EncounterRequest request) {
        validateEncounterRequest(request);

        Encounter encounter = findEncounterOrThrow(encounterId);
        Patient patient = findPatientOrThrow(request.getPatientId());

        encounter.setPatient(patient);
        applyRequestToEntity(request, encounter);

        Encounter saved = encounterRepository.save(encounter);
        return toResponse(saved);
    }

    public void deleteEncounter(Long encounterId) {
        Encounter encounter = findEncounterOrThrow(encounterId);
        encounterRepository.delete(encounter);
    }

    private Patient findPatientOrThrow(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Patient not found with id: " + patientId
                        )
                );
    }

    private Encounter findEncounterOrThrow(Long encounterId) {
        return encounterRepository.findById(encounterId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Encounter not found with id: " + encounterId
                        )
                );
    }

    private void validateEncounterRequest(EncounterRequest request) {

        if (request == null) {
            throw new IllegalArgumentException("Encounter request must not be null");
        }

        if (request.getPatientId() == null) {
            throw new IllegalArgumentException("Patient ID is required");
        }

        if (request.getEncounterDate() == null) {
            throw new IllegalArgumentException("Encounter date is required");
        }

        if (request.getEncounterType() == null || request.getEncounterType().isBlank()) {
            throw new IllegalArgumentException("Encounter type is required");
        }

        if (request.getStatus() == null || request.getStatus().isBlank()) {
            throw new IllegalArgumentException("Status is required");
        }
    }

    private void applyRequestToEntity(EncounterRequest request, Encounter encounter) {
        encounter.setEncounterDate(request.getEncounterDate());
        encounter.setEncounterType(request.getEncounterType());
        encounter.setDiagnosis(request.getDiagnosis());
        encounter.setStatus(request.getStatus());
        encounter.setNotes(request.getNotes());
    }

    private EncounterResponse toResponse(Encounter encounter) {
        return new EncounterResponse(
                encounter.getEncounterId(),
                encounter.getPatient().getPatientId(),
                encounter.getEncounterDate(),
                encounter.getEncounterType(),
                encounter.getDiagnosis(),
                encounter.getStatus(),
                encounter.getNotes(),
                encounter.getCreatedAt()
        );
    }
}