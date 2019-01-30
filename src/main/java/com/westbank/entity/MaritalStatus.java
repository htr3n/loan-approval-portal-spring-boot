package com.westbank.entity;

public enum MaritalStatus {
    OTHER(0),
    NOT_MARRIED(1),
    MARRIED(2),
    DIVORCED(3);

    private int dbValue;

    MaritalStatus(int dbvalue) {
        this.dbValue = dbvalue;
    }

    public int getDbValue() {
        return dbValue;
    }

}
