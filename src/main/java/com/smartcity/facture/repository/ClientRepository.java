package com.smartcity.facture.repository;

import com.smartcity.facture.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByRefClient(String refClient);
}
