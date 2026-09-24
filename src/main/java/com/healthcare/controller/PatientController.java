package com.healthcare.controller;

import com.healthcare.dto.PatientRequest;
import com.healthcare.dto.PatientResponse;
import com.healthcare.exception.PatientNotFoundException;
import com.healthcare.service.PatientService;
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

/**
 * Thin REST layer for Patient CRUD. All business logic lives in
 * PatientService - this class only translates HTTP <-> service calls.
 *
 * There's no @ControllerAdvice yet (that's a later phase), so
 * PatientNotFoundException is caught locally here and turned into a 404.
 */
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientResponse> createPatient(@RequestBody PatientRequest request) {
        PatientResponse response = patientService.createPatient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatientById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(patientService.getPatientById(id));
        } catch (PatientNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> updatePatient(@PathVariable("id") Long id,
                                                         @RequestBody PatientRequest request) {
        try {
            return ResponseEntity.ok(patientService.updatePatient(id, request));
        } catch (PatientNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") Long id) {
        try {
            patientService.deletePatient(id);
            return ResponseEntity.noContent().build();
        } catch (PatientNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}