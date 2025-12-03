package com.smartcity.facture.services;

import com.smartcity.facture.models.Client;
import com.smartcity.facture.repository.ClientRepository;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@WebService(serviceName = "ClientService")
public class ClientServiceImp {

    private final ClientRepository clientRepo;

    @Autowired
    public ClientServiceImp(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    @WebMethod(operationName = "getClientCount")
    public long countClients() {
        return clientRepo.count();
    }

    @WebMethod(operationName = "getAllClients")
    public Client[] getAllClients() {
        List<Client> list= clientRepo.findAll();
        System.out.println("Liste des clients: "+list);
        return list.toArray(new Client[0]);
    }
}
