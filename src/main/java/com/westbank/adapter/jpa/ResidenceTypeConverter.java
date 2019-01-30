package com.westbank.adapter.jpa;

import com.westbank.entity.ResidenceType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * https://dzone.com/articles/mapping-enums-done-right
 */
@Converter(autoApply = true)
public class ResidenceTypeConverter implements AttributeConverter<ResidenceType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ResidenceType residenceType) {
        if (residenceType != null)
            return residenceType.getDbValue();
        return ResidenceType.OTHER.getDbValue();
    }

    @Override
    public ResidenceType convertToEntityAttribute(Integer s) {
        if (s == null)
            return null;
        for (ResidenceType value : ResidenceType.values()) {
            if (value.getDbValue() == s)
                return value;
        }
        throw new IllegalArgumentException("Unknown value " + s);
    }
}
