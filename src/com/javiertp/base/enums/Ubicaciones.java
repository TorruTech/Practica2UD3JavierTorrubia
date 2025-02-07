package com.javiertp.base.enums;

public enum Ubicaciones {
    ZARAGOZA("Zaragoza"),
    MADRID("Madrid"),
    SEVILLA("Sevilla"),
    VALENCIA("Valencia"),
    BARCELONA("Barcelona"),
    LLEIDA("Lérida"),
    GIRONA("Girona"),
    PALMA("Palma"),
    TARRAGONA("Tarragona");

    private String value;

    Ubicaciones(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}