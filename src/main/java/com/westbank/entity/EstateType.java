package com.westbank.entity;

public enum EstateType {
    OTHER(0),
    HOUSE(1),
    FLAT(2),
    PARKING(3);

    private final int dbValue;

    EstateType(int value) {
        this.dbValue = value;
    }

    public int getDbValue() {
        return dbValue;
    }

}
