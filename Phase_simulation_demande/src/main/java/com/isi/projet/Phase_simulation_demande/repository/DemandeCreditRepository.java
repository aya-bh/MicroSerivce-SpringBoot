package com.isi.projet.Phase_simulation_demande.repository;

import com.isi.projet.Phase_simulation_demande.model.DemandeCredit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeCreditRepository extends JpaRepository<DemandeCredit, Long> {
}
