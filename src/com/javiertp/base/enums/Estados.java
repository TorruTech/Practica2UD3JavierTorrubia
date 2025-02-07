package com.javiertp.base.enums;

public enum Estados {
    CONFIRMADO("Confirmado"),
    PENDIENTE("Pendiente"),
    RECHAZADO("Rechazado");

    private String value;

    Estados(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}