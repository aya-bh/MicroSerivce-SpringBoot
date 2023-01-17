package com.isi.projet.Phase_simulation_demande.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refDossier")
    private Long id;

    @OneToOne
    @JoinColumn(name = "Demande_credit")
    private DemandeCredit demandeCredit;
    @OneToOne
    @JoinColumn(name = "bareme_id")
    private Bareme bareme;

    private float mensualite;

    private float interet;


}
