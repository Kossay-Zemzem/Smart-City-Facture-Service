package com.smartcity.facture.models;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.*;

import java.util.Date;
@XmlRootElement(name = "factureEau")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FactureEau {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String refFacture;
    private Double prixUnitaire; //in Dinars par kWh
    private Integer consomationTotal; //in
    private Double montantHT;
    private Double montantTTC;
    private Date dateEmission;
    private Date dateLimitePaiement;
    // Autre a ajouter comme duree de facturation, type de facture, etc.
    @XmlTransient
    @ManyToOne
    private Client client;
}
