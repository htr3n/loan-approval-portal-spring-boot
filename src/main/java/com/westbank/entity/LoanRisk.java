package com.westbank.entity;

public enum LoanRisk {
    NOTASSESSED(0),
    HIGH(1),
    MEDIUM(2),
    LOW(3);

    private final int dbValue;

    LoanRisk(int dbValue) {
        this.dbValue = dbValue;
    }

    public int getDbValue() {
        return dbValue;
    }

}
