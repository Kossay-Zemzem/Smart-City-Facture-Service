package com.smartcity.facture.repository;

import com.smartcity.facture.models.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture,Integer> {

}
