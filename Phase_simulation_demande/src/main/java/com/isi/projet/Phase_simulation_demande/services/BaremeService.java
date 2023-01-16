package com.isi.projet.Phase_simulation_demande.services;

import com.isi.projet.Phase_simulation_demande.model.Bareme;
import com.isi.projet.Phase_simulation_demande.repository.BaremeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaremeService {
    @Autowired
    public BaremeRepository baremeRepository;

    public Bareme createBareme (Bareme bareme) {
        return baremeRepository.save(bareme);
    }

    //READ
    public List<Bareme> getBareme() {
        return baremeRepository.findAll();
    }

    //DELETE
    public void deleteBareme(Long baremeId) {
        baremeRepository.deleteById(baremeId);
    }

    // UPDATE

    public Bareme updateBareme(Long baremeId, Bareme bareme) {
        Bareme b = baremeRepository.findById(baremeId).get();
        b.setDuree_maximal(bareme.getDuree_maximal());
        b.setDuree_minimal(bareme.getDuree_minimal());
        b.setTaux_nominal(bareme.getTaux_nominal());
        b.setMontant_minimal(bareme.getMontant_minimal());
        b.setMontant_maximal(bareme.getMontant_maximal());
        return baremeRepository.save(b);
    }

}
