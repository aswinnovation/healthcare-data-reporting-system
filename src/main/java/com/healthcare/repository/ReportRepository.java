package com.healthcare.repository;

import com.healthcare.entity.Encounter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Read-only reporting repository over the existing ENCOUNTER table.
 *
 * Extends the bare Spring Data "Repository" marker (not JpaRepository) on
 * purpose: reports have no business exposing save/delete/findAll on
 * Encounter - this interface exists only for the two aggregate queries
 * below, plus the PL/SQL-backed method from ReportRepositoryCustom
 * (implemented in ReportRepositoryImpl).
 *
 * Both @Query methods use nativeQuery = true against the actual Oracle
 * column names (status, encounter_type) rather than JPQL entity/field
 * names, as verified against Encounter.java's @Column mappings.
 */
public interface ReportRepository extends Repository<Encounter, Long>, ReportRepositoryCustom {

    @Query(value = "SELECT status AS status, COUNT(*) AS cnt FROM encounter GROUP BY status",
            nativeQuery = true)
    List<Object[]> countEncountersByStatus();

    @Query(value = "SELECT encounter_type AS encounter_type, COUNT(*) AS cnt FROM encounter GROUP BY encounter_type",
            nativeQuery = true)
    List<Object[]> countEncountersByType();
}