package com.smartcity.facture.repository;

import com.smartcity.facture.models.FactureEau;
import com.smartcity.facture.models.FactureParking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureParkingRepository extends JpaRepository<FactureParking,Integer> {

}
