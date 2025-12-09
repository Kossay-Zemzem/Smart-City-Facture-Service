package com.smartcity.facture.services;

import com.smartcity.facture.models.Client;
import com.smartcity.facture.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepo;


    public ClientService(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    public long countClients() {
        return clientRepo.count();
    }

    public Client[] getAllClients() {
        List<Client> list= clientRepo.findAll();
        System.out.println("Liste des clients: "+list);
        return list.toArray(new Client[0]);
    }

    public Client findClientByRef(String refClient) {
        return clientRepo.findByRefClient(refClient);
    }
}
