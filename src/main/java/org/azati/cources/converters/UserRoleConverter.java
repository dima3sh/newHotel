package org.azati.cources.converters;

import org.azati.cources.enums.UserRoles;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserRoleConverter implements AttributeConverter<UserRoles, String> {
    @Override
    public String convertToDatabaseColumn(UserRoles attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.toString();
    }

    @Override
    public UserRoles convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            return UserRoles.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
