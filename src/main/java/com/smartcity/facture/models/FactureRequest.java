package com.smartcity.facture.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@XmlRootElement(name = "factureRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FactureRequest {
    private String refFacture;
    private String refClient;
    private String codeSpot;
    private String typeSpot; // "VIP" or "NORMAL"
    private boolean isOccupied;
    private String etageLabel;
    private Integer durationInHours;
    private Double prixTTC;
}
