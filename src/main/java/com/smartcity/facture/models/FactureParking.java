package com.smartcity.facture.models;


import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@XmlRootElement(name = "factureParking")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FactureParking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String refFacture;
    //Spot details
    private String codeSpot; // e.g., "A1", "B2"
    @Enumerated(EnumType.STRING)
    private ParkingSpotType typeSpot; // e.g., "normal", "VIP"
    private boolean isOccupied;
    //---
    private String etageLabel;
    private Integer durationInHours;
    private Double prixTTC;
    @XmlTransient
    @ManyToOne
    private Client client;
}
