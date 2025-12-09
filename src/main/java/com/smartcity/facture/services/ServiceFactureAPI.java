package com.smartcity.facture.services;

import com.smartcity.facture.models.*;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@WebService(serviceName = "ServiceFactureAPI")
public class ServiceFactureAPI {

    private final FactureService factureService;
    private final ClientService clientService;

    @Autowired
    public ServiceFactureAPI(FactureService factureService, ClientService clientService) {
        this.factureService = factureService;
        this.clientService = clientService;
    }

//    @WebMethod(operationName = "getAllFactures")
//    public FactureElect[] getAllFactures() {
//        return factureService.getAllFactures();
//    }

    @WebMethod(operationName = "getAllFactureParType")
    public List<FactureDTO> getAllFactureParType(@WebParam(name = "type") TypeFacture type) {
        return factureService.getAllFacturesByType(type);
    }

    @WebMethod(operationName = "getClientCount")
    public long countClients() {
        return clientService.countClients();
    }

    @WebMethod(operationName = "getAllClients")
    public Client[] getAllClients() {
        return clientService.getAllClients();
    }

    //Generation de facture de parking
    @WebMethod(operationName = "generateFacture")
    public FactureParking generateFacture(
            @WebParam(name = "factureRequest") FactureRequest request
    ) {
        return factureService.generateFactureParking(request);
    }

}
