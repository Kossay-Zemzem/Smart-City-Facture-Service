package com.smartcity.facture.services;

import com.smartcity.facture.models.Facture;
import com.smartcity.facture.repository.ClientRepository;
import com.smartcity.facture.repository.FactureRepository;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactureService {
    private final FactureRepository factureRepo ;

    @Autowired
    public FactureService(FactureRepository factureRepo) {
        this.factureRepo = factureRepo;
    }


    public Facture[] getAllFactures() {
        return factureRepo.findAll().toArray(new Facture[0]);
    }
}
