package com.westbank.adapter.jpa;

import com.westbank.entity.LoanRisk;
import com.westbank.entity.MaritalStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LoanRiskConverter implements AttributeConverter<LoanRisk, Integer> {

    @Override
    public Integer convertToDatabaseColumn(LoanRisk loanRisk) {
        if (loanRisk != null)
            return loanRisk.getDbValue();
        return LoanRisk.NOTASSESSED.getDbValue();
    }

    @Override
    public LoanRisk convertToEntityAttribute(Integer s) {
        if (s == null)
            return null;
        for (LoanRisk value : LoanRisk.values()) {
            if (value.getDbValue() == s)
                return value;
        }
        throw new IllegalArgumentException("Unknown value " + s);
    }

}
