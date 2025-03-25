package com.devmatheusmarques.bibliotech_api.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        if (status == null) {
            return null;
        }
        return status == Status.ACTIVE ? "Ativo" : "Inativo";
    }

    @Override
    public Status convertToEntityAttribute(String descricao) {
        if (descricao == null) {
            return null;
        }
        return "Ativo".equalsIgnoreCase(descricao) ? Status.ACTIVE : Status.INACTIVE;
    }
}

