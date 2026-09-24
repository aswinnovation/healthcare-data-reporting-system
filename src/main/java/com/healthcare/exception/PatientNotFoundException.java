package com.healthcare.exception;

/**
 * Thrown by PatientService when a lookup/update/delete targets a
 * patient_id that doesn't exist.
 *
 * This is NOT wired to a @ControllerAdvice / global exception handler -
 * that's a future-phase concern. For Phase 2, PatientController catches
 * this exception directly and maps it to a 404 response.
 */
public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException(Long patientId) {
        super("Patient not found with id: " + patientId);
    }
}