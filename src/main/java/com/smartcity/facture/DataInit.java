package com.smartcity.facture;

import com.smartcity.facture.models.Client;
import com.smartcity.facture.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInit {

    @Bean
    CommandLineRunner initDatabase(ClientRepository clientRepository) {
        return args -> {
            Client client1 = new Client();
            client1.setNom("Smith");
            client1.setAdresse("456 Elm St");
//            client1.setNom("Doe");
//            client1.setAdresse("123 Main St");
            clientRepository.save(client1);
        };
    }
}
