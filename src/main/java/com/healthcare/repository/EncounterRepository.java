package com.healthcare.repository;

import com.healthcare.entity.Encounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Standard Spring Data JPA repository for Encounter.
 *
 * findByPatient_PatientId is the one custom query method, added for
 * EncounterService.getEncountersByPatientId() so that filtering by patient
 * happens as a SQL WHERE clause instead of loading every encounter and
 * filtering in Java.
 */
@Repository
public interface EncounterRepository extends JpaRepository<Encounter, Long> {

    List<Encounter> findByPatient_PatientId(Long patientId);
}