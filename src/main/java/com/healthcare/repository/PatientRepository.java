package com.healthcare.repository;

import com.healthcare.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Standard Spring Data JPA repository for Patient.
 * No custom queries yet - Phase 2 only needs the CRUD operations
 * JpaRepository already provides.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}