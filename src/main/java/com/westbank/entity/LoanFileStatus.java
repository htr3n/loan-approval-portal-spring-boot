package com.westbank.entity;

public enum LoanFileStatus {
    INITIALIZED(0),
    UNDER_CONSIDERATION(1),
    WORTHINESS_ANALYSIS(2),
    RISK_ANALYSIS(3),
    APPROVED(4),
    REJECTED(5),
    CANCELED(6),
    ACCEPTED(7);

    private Integer dbValue;

    LoanFileStatus(int dbValue) {
        this.dbValue = dbValue;
    }

    public Integer getDbValue() {
        return this.dbValue;
    }

    public static LoanFileStatus getLoanFileStatus(int value) {
        switch (value) {
            case 0:
                return INITIALIZED;
            case 1:
                return UNDER_CONSIDERATION;
            case 2:
                return WORTHINESS_ANALYSIS;
            case 3:
                return RISK_ANALYSIS;
            case 4:
                return APPROVED;
            case 5:
                return REJECTED;
            case 6:
                return CANCELED;
            case 7:
                return ACCEPTED;
            default:
                throw new IllegalArgumentException("Unknown " + value);
        }
    }
}
