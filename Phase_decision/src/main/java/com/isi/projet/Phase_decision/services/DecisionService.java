package com.isi.projet.Phase_decision.services;

import com.isi.projet.Phase_decision.Repository.DecisionRepository;
import com.isi.projet.Phase_decision.enums.Statut;
import com.isi.projet.Phase_decision.model.Decision;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DecisionService {
    @Autowired
    public DecisionRepository decisionRepository;

    public Decision createDecision (Decision d) {

        return decisionRepository.save(d);
    }
    //READ
    public List<Decision> getDecision() {
        return decisionRepository.findAll();
    }

    //DELETE
    public void deleteDecision(Long decId) {
        decisionRepository.deleteById(decId);
    }

    // UPDATE

    public Decision updateDecision(Long decId, Decision decision) {
        Decision c = decisionRepository.findById(decId).get();
        c.setRef_demande(decision.getRef_demande());
        c.setDate_decision(decision.getDate_decision());
        c.setStatut(decision.getStatut());

        return decisionRepository.save(c);
    }

    public Decision addDecision(Long id, JSONObject json){
        Decision decision = decisionRepository.findById(id).get();
        decision.setDate_decision(new Date());
        decision.setRef_demande(json.get("dossier").toString());
        if(json.get("eval_score").equals("ROUGE")){
            decision.setStatut(Statut.REFUS);
        }
        else {
            decision.setStatut(Statut.ACCEPTATION);
        }
        return decisionRepository.save(decision);
    }

}
