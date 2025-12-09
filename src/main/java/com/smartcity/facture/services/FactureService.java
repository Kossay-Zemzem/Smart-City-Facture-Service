package com.smartcity.facture.services;

import com.smartcity.facture.models.*;
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
    private final ClientService clientService;
    private final FactureElectRepository factureElectRepository;
    private final FactureParkingRepository factureParkingRepository;
    private final FactureEauRepository factureEauRepository;


    @Autowired
    public FactureService(ClientRepository clientRepository, ClientService clientService, FactureElectRepository factureElectRepository
            , FactureParkingRepository factureParkingRepository, FactureEauRepository factureEauRepository) {
        this.clientService = clientService;
        this.factureElectRepository = factureElectRepository;
        this.factureParkingRepository = factureParkingRepository;
        this.factureEauRepository = factureEauRepository;
    }


//    public FactureElect[] getAllFactures() {
//        return factureRepo.findAll().toArray(new FactureElect[0]);
//    }

    public List<FactureDTO> getAllFacturesByType(TypeFacture type) {
        switch (type) {
            case ELECT:
                return factureElectRepository.findAll().stream()
                        .map(this::convertElectToDTO)
                        .collect(Collectors.toList());
            case EAU:
                return factureEauRepository.findAll().stream()
                        .map(this::convertEauToDTO)
                        .collect(Collectors.toList());
            case PARKING:
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
        dto.setType(TypeFacture.ELECT); // Using enum
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
        dto.setType(TypeFacture.EAU); // Using enum
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
        dto.setType(TypeFacture.PARKING); // Using enum

        // Core Facture fields
        dto.setId(facture.getId());
        dto.setRefFacture(facture.getRefFacture());
        // Client details (existing logic)
        if (facture.getClient() != null) {
            dto.setClientRef(facture.getClient().getRefClient());
            dto.setClientNom(facture.getClient().getNom());
        }

        // New FactureParking fields
        dto.setCodeSpot(facture.getCodeSpot());
        dto.setTypeSpot(facture.getTypeSpot());
        dto.setOccupied(facture.isOccupied());
        dto.setEtageLabel(facture.getEtageLabel());
        dto.setDurationInHours(facture.getDurationInHours());

        dto.setMontantTTC(facture.getPrixTTC());

        return dto;
    }

    //Generation de facture de parking
    public FactureParking generateFactureParking(FactureRequest request) {
        // Find or create client
        Client client = clientService.findClientByRef(request.getRefClient());
        if (client == null) {
            //TODO LOG a WARN here uing Log4J
            System.out.println("Client not found: " + request.getRefClient());
            return null; // Or throw an exception
        }
        // Create facture
        FactureParking facture = new FactureParking();
        facture.setRefFacture(request.getRefFacture());
        facture.setCodeSpot(request.getCodeSpot());
        facture.setTypeSpot(ParkingSpotType.valueOf(request.getTypeSpot()));
        facture.setOccupied(request.isOccupied());
        facture.setEtageLabel(request.getEtageLabel());
        facture.setDurationInHours(request.getDurationInHours());
        facture.setPrixTTC(request.getPrixTTC());
        facture.setClient(client);
        // Save and return
        return factureParkingRepository.save(facture);
    }
}
