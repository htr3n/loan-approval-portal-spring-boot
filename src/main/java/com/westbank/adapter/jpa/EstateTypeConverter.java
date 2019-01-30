package com.westbank.adapter.jpa;

import com.westbank.entity.EstateType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * https://dzone.com/articles/mapping-enums-done-right
 */
@Converter(autoApply = true)
public class EstateTypeConverter implements AttributeConverter<EstateType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EstateType estateType) {
        if (estateType != null)
            return estateType.getDbValue();
        return EstateType.OTHER.getDbValue();
    }

    @Override
    public EstateType convertToEntityAttribute(Integer s) {
        if (s == null)
            return null;
        for (EstateType value : EstateType.values()) {
            if (value.getDbValue() == s)
                return value;
        }
        throw new IllegalArgumentException("Unknown value " + s);
    }
}
