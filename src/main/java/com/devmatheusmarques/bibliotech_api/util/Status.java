package com.devmatheusmarques.bibliotech_api.util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    ACTIVE("Ativo"),
    INACTIVE("Inativo");

    private final String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    public static Status fromString(String text) {
        for (Status status : Status.values()) {
            if (status.descricao.equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + text);
    }
}
