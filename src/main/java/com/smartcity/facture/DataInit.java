package com.smartcity.facture;

import com.smartcity.facture.models.Client;
import com.smartcity.facture.models.Facture;
import com.smartcity.facture.repository.ClientRepository;
import com.smartcity.facture.repository.FactureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class DataInit {

    @Bean
    CommandLineRunner initDatabase(ClientRepository clientRepository, FactureRepository factureRepository) {
        return args -> {
            // facture1.set
            Client client1 = new Client();
            client1.setNom("Smith");
            client1.setRefClient("C001");
            client1.setBusiness(false);
            clientRepository.save(client1);

            Facture facture1 = new Facture();
            facture1.setRefFacture("F001");
            facture1.setPrixUnitaire(0.15);
            facture1.setConsomationTotal(100);
            facture1.setMontantHT(facture1.getPrixUnitaire() * facture1.getConsomationTotal());
            facture1.setMontantTTC(facture1.getMontantHT() * 1.19); // Assuming 19% tax
            facture1.setClient(client1);
            factureRepository.save(facture1);


        };
    }
}
