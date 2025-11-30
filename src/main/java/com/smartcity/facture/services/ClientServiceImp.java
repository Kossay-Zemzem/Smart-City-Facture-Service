package com.smartcity.facture.services;

import com.smartcity.facture.repository.ClientRepository;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@WebService(serviceName = "ClientService" )
public class ClientServiceImp {

    private final ClientRepository clientRepo ;

    @Autowired
    public ClientServiceImp(ClientRepository clientRepo){
        this.clientRepo=clientRepo;
    }

    @WebMethod(operationName = "getClientCount" )
    public long countClients(){
        return clientRepo.count();
    }
}
