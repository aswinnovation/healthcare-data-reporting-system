package com.healthcare.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import java.sql.Types;

/**
 * Implements the ReportRepositoryCustom fragment of ReportRepository.
 *
 * Calls the read-only Oracle PL/SQL function GET_PATIENT_ENCOUNTER_COUNT
 * (see src/main/resources/db/phase4_report_function.sql) via SimpleJdbcCall.
 * Parameters are declared explicitly with withoutProcedureColumnMetaDataAccess()
 * rather than relying on JDBC metadata introspection, which is the more
 * predictable/portable approach for calling Oracle functions from Spring.
 */
@Repository
public class ReportRepositoryImpl implements ReportRepositoryCustom {

    private static final String FUNCTION_NAME = "GET_PATIENT_ENCOUNTER_COUNT";
    private static final String RETURN_PARAM = "RETURN_VALUE";
    private static final String PATIENT_ID_PARAM = "P_PATIENT_ID";

    private final SimpleJdbcCall getPatientEncounterCountCall;

    public ReportRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.getPatientEncounterCountCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName(FUNCTION_NAME)
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlOutParameter(RETURN_PARAM, Types.NUMERIC),
                        new SqlParameter(PATIENT_ID_PARAM, Types.NUMERIC)
                );
    }

    @Override
    public Long countEncountersForPatientViaProcedure(Long patientId) {
        Number result = getPatientEncounterCountCall.executeFunction(Number.class, patientId);
        return result == null ? 0L : result.longValue();
    }
}