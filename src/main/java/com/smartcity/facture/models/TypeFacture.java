package com.smartcity.facture.models;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "TypeFacture")
@XmlEnum
public enum TypeFacture {
    @XmlEnumValue("ELECT")
    ELECT("ELECT"),

    @XmlEnumValue("EAU")
    EAU("EAU"),

    @XmlEnumValue("PARKING")
    PARKING("PARKING");

    private final String value;

    TypeFacture(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TypeFacture fromValue(String value) {
        for (TypeFacture type : TypeFacture.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Type de facture invalide: " + value);
    }
}
