package com.healthcare.service;

import com.healthcare.dto.PatientRequest;
import com.healthcare.dto.PatientResponse;
import com.healthcare.entity.Patient;
import com.healthcare.exception.PatientNotFoundException;
import com.healthcare.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Holds all Patient business logic. Controller stays thin and delegates
 * here; this class is the only place that talks to PatientRepository.
 */
@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientResponse createPatient(PatientRequest request) {
        validatePatientRequest(request);

        Patient patient = new Patient();
        applyRequestToEntity(request, patient);

        Patient saved = patientRepository.save(patient);
        return toResponse(saved);
    }

    public PatientResponse getPatientById(Long patientId) {
        Patient patient = findPatientOrThrow(patientId);
        return toResponse(patient);
    }

    public List<PatientResponse> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public PatientResponse updatePatient(Long patientId, PatientRequest request) {
        validatePatientRequest(request);

        Patient patient = findPatientOrThrow(patientId);
        applyRequestToEntity(request, patient);

        Patient saved = patientRepository.save(patient);
        return toResponse(saved);
    }

    public void deletePatient(Long patientId) {
        Patient patient = findPatientOrThrow(patientId);
        patientRepository.delete(patient);
    }

    private Patient findPatientOrThrow(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(patientId));
    }

    private void validatePatientRequest(PatientRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Patient request must not be null");
        }

        if (request.getFirstName() == null || request.getFirstName().isBlank()) {
            throw new IllegalArgumentException("First name is required");
        }

        if (request.getLastName() == null || request.getLastName().isBlank()) {
            throw new IllegalArgumentException("Last name is required");
        }

        if (request.getDateOfBirth() == null) {
            throw new IllegalArgumentException("Date of birth is required");
        }

        if (request.getGender() == null || request.getGender().isBlank()) {
            throw new IllegalArgumentException("Gender is required");
        }
    }

    private void applyRequestToEntity(PatientRequest request, Patient patient) {
        patient.setFirstName(request.getFirstName());
        patient.setLastName(request.getLastName());
        patient.setDateOfBirth(request.getDateOfBirth());
        patient.setGender(request.getGender());
        patient.setPhone(request.getPhone());
        patient.setEmail(request.getEmail());
    }

    private PatientResponse toResponse(Patient patient) {
        return new PatientResponse(
                patient.getPatientId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getDateOfBirth(),
                patient.getGender(),
                patient.getPhone(),
                patient.getEmail(),
                patient.getCreatedAt()
        );
    }
}