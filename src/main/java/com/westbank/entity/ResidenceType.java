package com.westbank.entity;

public enum ResidenceType {
    OTHER(0),
    MAIN_HOUSE(1),
    SECOND_HOUSE(2);

    private final int dbValue;

    ResidenceType(int dbValue) {
        this.dbValue = dbValue;
    }

    public int getDbValue() {
        return dbValue;
    }
}
