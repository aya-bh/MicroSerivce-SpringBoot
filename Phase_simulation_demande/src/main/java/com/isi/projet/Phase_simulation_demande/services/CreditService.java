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

    @Autowired
    public BaremeRepository baremeRepository;

    public Bareme lookForBareme(int duree, float montant){
        Bareme bar = new Bareme() ;
        List<Bareme> bareme  = baremeRepository.findAll();
        ArrayList<Bareme> preselect = new ArrayList<Bareme>();
        bareme.forEach((br) -> {
            if(br.getDuree_minimal() <= duree && br.getDuree_maximal() >= duree){
                //System.out.println("vrai duree");
                if(br.getMontant_minimal() <= montant && br.getMontant_maximal() >= montant){
                   // System.out.println("vrai montant");
                    //System.out.println("Bareme :"+br);
                    preselect.add(preselect.size(),br);
                }
            }
        });
        //System.out.println("preselect : "+preselect);
        float min = 100;
        preselect.forEach((br)->{
            if(br.getTaux_nominal() <= min){
                bar.setId(br.getId());
                bar.setMontant_minimal(br.getMontant_minimal());
                bar.setMontant_maximal(br.getMontant_maximal());
                bar.setDuree_minimal(br.getDuree_minimal());
                bar.setDuree_maximal(br.getDuree_maximal());
                bar.setTaux_nominal(br.getTaux_nominal());
            }
        });
        //System.out.println("Bareme selectionné : "+bar);
        return bar;
    }
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
