package com.healthcare.dto;

/**
 * One row of the "encounters grouped by type" report
 * (GET /api/reports/encounters/type-summary).
 */
public class EncounterTypeSummaryResponse {

    private String encounterType;
    private Long count;

    public EncounterTypeSummaryResponse() {
    }

    public EncounterTypeSummaryResponse(String encounterType, Long count) {
        this.encounterType = encounterType;
        this.count = count;
    }

    public String getEncounterType() {
        return encounterType;
    }

    public void setEncounterType(String encounterType) {
        this.encounterType = encounterType;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}