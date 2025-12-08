package com.smartcity.facture.services;

import com.smartcity.facture.models.Client;
import com.smartcity.facture.models.FactureElect;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@WebService(serviceName = "ServiceFactureAPI")
public class ServiceFactureAPI {

    private final FactureService factureService;
    private final ClientServiceImp clientService;

    @Autowired
    public ServiceFactureAPI(FactureService factureService, ClientServiceImp clientService) {
        this.factureService = factureService;
        this.clientService = clientService;
    }

    @WebMethod(operationName = "getAllFactures")
    public FactureElect[] getAllFactures() {
        return factureService.getAllFactures();
    }


    @WebMethod(operationName = "getClientCount")
    public long countClients() {
        return clientService.countClients();
    }

    @WebMethod(operationName = "getAllClients")
    public Client[] getAllClients() {
        return clientService.getAllClients();
    }
}
