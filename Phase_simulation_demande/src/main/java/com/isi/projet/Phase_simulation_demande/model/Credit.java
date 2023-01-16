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
    private float montant_credit;
    private int duree_credit;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "bareme_id")
    private Bareme bareme;

    private float mensualite;

    private float interet;


}
