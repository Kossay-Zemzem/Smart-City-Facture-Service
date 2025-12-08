package com.smartcity.facture.services;

import com.smartcity.facture.models.FactureElect;
import com.smartcity.facture.repository.FactureElectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactureService {
    private final FactureElectRepository factureRepo ;

    @Autowired
    public FactureService(FactureElectRepository factureRepo) {
        this.factureRepo = factureRepo;
    }


    public FactureElect[] getAllFactures() {
        return factureRepo.findAll().toArray(new FactureElect[0]);
    }
}
