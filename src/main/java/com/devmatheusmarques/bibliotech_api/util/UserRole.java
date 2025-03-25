package com.devmatheusmarques.bibliotech_api.util;

public enum UserRole {

    ADMIN("Administrador"),
    USER("Usuário");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static UserRole fromString(String text) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.role.equalsIgnoreCase(text)) {
                return userRole;
            }
        }
        throw new IllegalArgumentException("Nível de acesso inválido: " + text);
    }
}
