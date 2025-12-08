package com.smartcity.facture.services;

import com.smartcity.facture.models.FactureDTO;
import com.smartcity.facture.models.FactureEau;
import com.smartcity.facture.models.FactureElect;
import com.smartcity.facture.models.FactureParking;
import com.smartcity.facture.repository.ClientRepository;
import com.smartcity.facture.repository.FactureEauRepository;
import com.smartcity.facture.repository.FactureElectRepository;
import com.smartcity.facture.repository.FactureParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FactureService {
    private final ClientRepository clientRepository;
    private final FactureElectRepository factureElectRepository;
    private final FactureParkingRepository factureParkingRepository;
    private final FactureEauRepository factureEauRepository;


    @Autowired
    public FactureService(ClientRepository clientRepository, FactureElectRepository factureElectRepository
            , FactureParkingRepository factureParkingRepository, FactureEauRepository factureEauRepository) {
        this.clientRepository = clientRepository;
        this.factureElectRepository = factureElectRepository;
        this.factureParkingRepository = factureParkingRepository;
        this.factureEauRepository = factureEauRepository;
    }


//    public FactureElect[] getAllFactures() {
//        return factureRepo.findAll().toArray(new FactureElect[0]);
//    }

    public List<FactureDTO> getAllFacturesByType(String type) {
        switch (type.toUpperCase()) {
            case "ELECT":
                return factureElectRepository.findAll().stream()
                        .map(this::convertElectToDTO)
                        .collect(Collectors.toList());
            case "EAU":
                return factureEauRepository.findAll().stream()
                        .map(this::convertEauToDTO)
                        .collect(Collectors.toList());
            case "PARKING":
                return factureParkingRepository.findAll().stream()
                        .map(this::convertParkingToDTO)
                        .collect(Collectors.toList());
            default:
                throw new IllegalArgumentException("Type de facture invalide: " + type);
        }
    }

    private FactureDTO convertElectToDTO(FactureElect facture) {
        FactureDTO dto = new FactureDTO();
        dto.setId(facture.getId());
        dto.setRefFacture(facture.getRefFacture());
        dto.setType("ELECT");
        dto.setPrixUnitaire(facture.getPrixUnitaire());
        dto.setConsomationTotal(facture.getConsomationTotal());
        dto.setPrixServiceSuppl(facture.getPrixServiceSuppl()); // Only for electricity
        dto.setMontantHT(facture.getMontantHT());
        dto.setMontantTTC(facture.getMontantTTC());
        dto.setDateEmission(facture.getDateEmission());
        dto.setDateLimitePaiement(facture.getDateLimitePaiement());

        if (facture.getClient() != null) {
            dto.setClientRef(facture.getClient().getRefClient());
            dto.setClientNom(facture.getClient().getNom());
        }

        return dto;
    }

    private FactureDTO convertEauToDTO(FactureEau facture) {
        FactureDTO dto = new FactureDTO();
        dto.setId(facture.getId());
        dto.setRefFacture(facture.getRefFacture());
        dto.setType("EAU");
        dto.setPrixUnitaire(facture.getPrixUnitaire());
        dto.setConsomationTotal(facture.getConsomationTotal());
        // Note: prixServiceSuppl is NOT set - will be excluded from response
        dto.setMontantHT(facture.getMontantHT());
        dto.setMontantTTC(facture.getMontantTTC());
        dto.setDateEmission(facture.getDateEmission());
        dto.setDateLimitePaiement(facture.getDateLimitePaiement());

        if (facture.getClient() != null) {
            dto.setClientRef(facture.getClient().getRefClient());
            dto.setClientNom(facture.getClient().getNom());
        }

        return dto;
    }

    private FactureDTO convertParkingToDTO(FactureParking facture) {
        FactureDTO dto = new FactureDTO();
        dto.setId(facture.getId());
        dto.setRefFacture(facture.getRefFacture());
        dto.setType("PARKING");
        // Note: no consumption or price fields set - will be excluded from response

        if (facture.getClient() != null) {
            dto.setClientRef(facture.getClient().getRefClient());
            dto.setClientNom(facture.getClient().getNom());
        }

        return dto;
    }
}
