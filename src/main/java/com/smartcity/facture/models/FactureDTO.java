package com.smartcity.facture.models;

import jakarta.persistence.Entity;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class FactureDTO {
    private Integer id;
    private String refFacture;
    private TypeFacture type; // Changed from String to TypeFacture
    private Double montantHT;
    private Double montantTTC;
    private Date dateEmission;
    private Date dateLimitePaiement;
    private Double prixUnitaire;
    private Integer consomationTotal;
    private Double prixServiceSuppl;
    private String clientRef;
    private String clientNom;

    //parking specific fields
    private String codeSpot;
    private ParkingSpotType typeSpot; // "VIP" or "NORMAL"
    private boolean isOccupied;
    private String etageLabel;
    private Integer durationInHours;

}
