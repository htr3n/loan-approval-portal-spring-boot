package com.westbank.adapter.jpa;

import com.westbank.entity.LoanFileStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LoanFileStatusConverter implements AttributeConverter<LoanFileStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(LoanFileStatus loanFileStatus) {
        if (loanFileStatus != null)
            return loanFileStatus.getDbValue();
        return null;
    }

    @Override
    public LoanFileStatus convertToEntityAttribute(Integer loanFileStatus) {
        if (loanFileStatus == null)
            return null;
        return LoanFileStatus.getLoanFileStatus(loanFileStatus);
    }
}
