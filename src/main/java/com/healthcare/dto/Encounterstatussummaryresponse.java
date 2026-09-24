package com.healthcare.dto;

/**
 * One row of the "encounters grouped by status" report
 * (GET /api/reports/encounters/status-summary).
 */
public class EncounterStatusSummaryResponse {

    private String status;
    private Long count;

    public EncounterStatusSummaryResponse() {
    }

    public EncounterStatusSummaryResponse(String status, Long count) {
        this.status = status;
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}