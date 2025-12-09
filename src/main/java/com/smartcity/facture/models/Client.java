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

import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"factureElects","factureEaus","factureParkings"})
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String refClient;
    private String nom;
    private boolean isBusiness;

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @XmlTransient
    private Set<FactureElect> factureElects =new HashSet<>();

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @XmlTransient
    private Set<FactureEau> factureEaus = new HashSet<>() ;

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @XmlTransient
    private Set<FactureParking> factureParkings = new HashSet<>() ;
}
