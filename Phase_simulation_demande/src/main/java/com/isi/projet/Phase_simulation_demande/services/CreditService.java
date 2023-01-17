package com.isi.projet.Phase_simulation_demande.services;

import com.isi.projet.Phase_simulation_demande.model.Bareme;
import com.isi.projet.Phase_simulation_demande.model.Credit;
import com.isi.projet.Phase_simulation_demande.repository.BaremeRepository;
import com.isi.projet.Phase_simulation_demande.repository.CreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditService {
    @Autowired
    public CreditRepository creditRepository;


    public void setterCredit(Credit c, Credit credit){
        c.setId(credit.getId());
        c.setDuree_credit(credit.getDuree_credit());
        c.setMontant_credit(credit.getMontant_credit());
        c.setClient(credit.getClient());
        c.setBareme(lookForBareme(credit.getDuree_credit(),credit.getMontant_credit()));
        float interet = c.getMontant_credit() * c.getBareme().getTaux_nominal();
        c.setInteret(interet);
        float mensualite = (c.getMontant_credit()+c.getInteret())/c.getDuree_credit();
        c.setMensualite(mensualite);
        //System.out.println("Credit ajouté : "+c);

    }
    public Credit createCredit (Credit credit) {
        Credit c = new Credit();
        setterCredit(c,credit);
        return creditRepository.save(c);
    }

    //READ
    public List<Credit> getCredit() {
        return creditRepository.findAll();
    }

    //DELETE
    public void deleteCredit(Long creditId) {
        creditRepository.deleteById(creditId);
    }

    public Credit updateCredit(Long creditId, Credit credit) {
        Credit c = creditRepository.findById(creditId).get();
        setterCredit(c,credit);
        return creditRepository.save(c);
    }

    public Credit sendCredit(Long id) {

        Credit cnv =creditRepository.getCredit(id);
        return cnv;
    }

}
