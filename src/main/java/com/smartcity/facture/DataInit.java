package com.smartcity.facture;

import com.smartcity.facture.models.Client;
import com.smartcity.facture.models.FactureEau;
import com.smartcity.facture.models.FactureElect;
import com.smartcity.facture.models.FactureParking;
import com.smartcity.facture.repository.ClientRepository;
import com.smartcity.facture.repository.FactureEauRepository;
import com.smartcity.facture.repository.FactureElectRepository;
import com.smartcity.facture.repository.FactureParkingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Calendar;

@Configuration
public class DataInit {

    @Bean
    CommandLineRunner initDatabase(ClientRepository clientRepository, FactureElectRepository factureElectRepository
    , FactureParkingRepository factureParkingRepository, FactureEauRepository factureEauRepository) {
        return args -> {
            // Create Client 1 - Individual
            Client client1 = new Client();
            client1.setRefClient("CLIENT-001");
            client1.setNom("Ahmed Ben Salah");
            client1.setBusiness(false);

            // Create Client 2 - Business
            Client client2 = new Client();
            client2.setRefClient("CLIENT-002");
            client2.setNom("Société TechnoServices SARL");
            client2.setBusiness(true);

            //Create client 3 - Individual
            Client client3 = new Client();
            client3.setRefClient("CLIENT-003");
            client3.setNom("Meriem Bouzid");
            client3.setBusiness(false);

            // Save clients first
            clientRepository.save(client1);
            clientRepository.save(client2);
            clientRepository.save(client3);

            // Create Electricity Invoices for Client 1
            FactureElect elect1 = new FactureElect();
            elect1.setRefFacture("ELECT-2024-001");
            elect1.setPrixUnitaire(0.150); // 0.150 TND per kWh
            elect1.setPrixServiceSuppl(5.0); // 5 TND for TV/Radio service
            elect1.setConsomationTotal(350); // 350 kWh
            elect1.setMontantHT(52.5); // 350 * 0.150
            elect1.setMontantTTC(68.25); // HT + TVA (19%) + service
            elect1.setDateEmission(new Date());
            elect1.setDateLimitePaiement(addDays(new Date(), 30));
            elect1.setClient(client1);

            FactureElect elect2 = new FactureElect();
            elect2.setRefFacture("ELECT-2024-002");
            elect2.setPrixUnitaire(0.150);
            elect2.setPrixServiceSuppl(5.0);
            elect2.setConsomationTotal(280);
            elect2.setMontantHT(42.0);
            elect2.setMontantTTC(54.98);
            elect2.setDateEmission(addDays(new Date(), -30));
            elect2.setDateLimitePaiement(new Date());
            elect2.setClient(client1);

            // Create Electricity Invoices for Client 2
            FactureElect elect3 = new FactureElect();
            elect3.setRefFacture("ELECT-2024-003");
            elect3.setPrixUnitaire(0.150);
            elect3.setPrixServiceSuppl(5.0);
            elect3.setConsomationTotal(1200); // Higher consumption for business
            elect3.setMontantHT(180.0);
            elect3.setMontantTTC(219.2);
            elect3.setDateEmission(new Date());
            elect3.setDateLimitePaiement(addDays(new Date(), 30));
            elect3.setClient(client2);

            // Create Water Invoices for Client 1
            FactureEau eau1 = new FactureEau();
            eau1.setRefFacture("EAU-2024-001");
            eau1.setPrixUnitaire(0.8); // 0.8 TND per m³
            eau1.setConsomationTotal(45); // 45 m³
            eau1.setMontantHT(36.0);
            eau1.setMontantTTC(42.84); // HT + TVA (19%)
            eau1.setDateEmission(new Date());
            eau1.setDateLimitePaiement(addDays(new Date(), 30));
            eau1.setClient(client1);

            FactureEau eau2 = new FactureEau();
            eau2.setRefFacture("EAU-2024-002");
            eau2.setPrixUnitaire(0.8);
            eau2.setConsomationTotal(38);
            eau2.setMontantHT(30.4);
            eau2.setMontantTTC(36.18);
            eau2.setDateEmission(addDays(new Date(), -30));
            eau2.setDateLimitePaiement(new Date());
            eau2.setClient(client1);

            // Create Water Invoices for Client 2
            FactureEau eau3 = new FactureEau();
            eau3.setRefFacture("EAU-2024-003");
            eau3.setPrixUnitaire(0.8);
            eau3.setConsomationTotal(150); // Higher consumption for business
            eau3.setMontantHT(120.0);
            eau3.setMontantTTC(142.8);
            eau3.setDateEmission(new Date());
            eau3.setDateLimitePaiement(addDays(new Date(), 30));
            eau3.setClient(client2);

            // Create Parking Invoices for Client 1
            FactureParking parking1 = new FactureParking();
            parking1.setRefFacture("PARK-2024-001");
            parking1.setClient(client3);

            FactureParking parking2 = new FactureParking();
            parking2.setRefFacture("PARK-2024-002");
            parking2.setClient(client1);

            // Create Parking Invoices for Client 2
            FactureParking parking3 = new FactureParking();
            parking3.setRefFacture("PARK-2024-003");
            parking3.setClient(client3);

            // Save all invoices
            factureElectRepository.save(elect1);
            factureElectRepository.save(elect2);
            factureElectRepository.save(elect3);

            factureEauRepository.save(eau1);
            factureEauRepository.save(eau2);
            factureEauRepository.save(eau3);

            factureParkingRepository.save(parking1);
            factureParkingRepository.save(parking2);
            factureParkingRepository.save(parking3);

            System.out.println("✓ Data initialization completed successfully!");
        };
    }
    // Helper method to add days to a date
    private Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
}
