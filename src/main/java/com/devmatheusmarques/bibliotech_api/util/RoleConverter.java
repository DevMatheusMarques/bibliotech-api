package com.devmatheusmarques.bibliotech_api.util;

import jakarta.persistence.AttributeConverter;

public class RoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole role) {
        if (role == null) {
            return null;
        }
        return role == UserRole.ADMIN ? "Administrador" : "Usuário";
    }

    @Override
    public UserRole convertToEntityAttribute(String role) {
        if (role == null) {
            return null;
        }
        return "Administrador".equalsIgnoreCase(role) ? UserRole.ADMIN : UserRole.USER;
    }
}
