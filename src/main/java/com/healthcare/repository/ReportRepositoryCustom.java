package com.healthcare.repository;

/**
 * Custom fragment for ReportRepository, implemented in ReportRepositoryImpl.
 * Separated out because this one method is backed by an Oracle PL/SQL
 * function call (SimpleJdbcCall), not a Spring Data @Query method - Spring
 * Data's "Impl" naming convention lets both live behind the single
 * ReportRepository the service depends on.
 */
public interface ReportRepositoryCustom {

    /**
     * Calls the GET_PATIENT_ENCOUNTER_COUNT Oracle PL/SQL function.
     *
     * @param patientId the patient to count encounters for
     * @return total encounters for that patient, straight from the database
     */
    Long countEncountersForPatientViaProcedure(Long patientId);
}