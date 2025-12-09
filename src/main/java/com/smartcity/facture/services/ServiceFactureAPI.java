package com.smartcity.facture.services;

import com.smartcity.facture.models.Client;
import com.smartcity.facture.models.FactureDTO;
import com.smartcity.facture.models.FactureElect;
import com.smartcity.facture.models.TypeFacture;
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
    private final ClientServiceImp clientService;

    @Autowired
    public ServiceFactureAPI(FactureService factureService, ClientServiceImp clientService) {
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

}
