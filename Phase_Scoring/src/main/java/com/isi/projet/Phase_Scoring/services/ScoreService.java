package com.isi.projet.Phase_Scoring.services;

import com.isi.projet.Phase_Scoring.Repository.BlacklisteBCTRepository;
import com.isi.projet.Phase_Scoring.Repository.ScoreRepository;
import com.isi.projet.Phase_Scoring.enums.Evaluation;
import com.isi.projet.Phase_Scoring.model.BlacklisteBCT;
import com.isi.projet.Phase_Scoring.model.Score;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class ScoreService {
    @Autowired
    public ScoreRepository scoreRepository;

    @Autowired
    public BlacklisteBCTRepository bctRepository;

    public Score createScore (Score score) {

        return scoreRepository.save(score);
    }
    //READ
    public List<Score> getScore() {
        return scoreRepository.findAll();
    }

    //DELETE
    public void deleteScore(Long scoreId) {
        scoreRepository.deleteById(scoreId);
    }

    // UPDATE

    public Score updateScore(Long scoreId, Score score) {
        Score c = scoreRepository.findById(scoreId).get();
        c.setCalc_score(score.getCalc_score());
        c.setEval_score(score.getEval_score());
        c.setDossier(score.getDossier());

        return scoreRepository.save(c);
    }

    public Score changeScore(Long id, JSONObject json) throws ParseException {
        int score = 0;
        Boolean trouve = false;
        List<BlacklisteBCT> bct  = bctRepository.findAll();
        JSONParser parser = new JSONParser();
        JSONObject client = (JSONObject) parser.parse(json.get("client").toString());
        for (BlacklisteBCT liste : bct){
            if(liste.getCin().equals(client.get("cin"))){
                trouve= true;
            }
        }


        if(trouve) {
            score = 0;
        }else {
            //Si salaire > 2000 ➔ Score = score + 20
            if ((double) client.get("salaire_mensuel") >= 2000) {
                score = score + 20;
            }
            //Si Salaire > 1000 et salaire <2000 ➔ Score = score + 10
            if ((double) client.get("salaire_mensuel") >= 1000 && (double) client.get("salaire_mensuel") < 2000) {
                score = score + 10;
            }
            //Si type contrat == CDI ➔ Score = score + 30
            if (client.get("type_contrat").equals("CDI")) {
                score = score + 30;
            }
            //Si Mensualité / salaire < 0.45 ➔ Score = score + 50
            if ((double) json.get("mensualite") / (double) client.get("salaire_mensuel") < 0.45) {
                score = score + 50;
            }
        }
        Score scr = scoreRepository.findById(id).get();
        scr.setDossier(json.get("id").toString());
        scr.setCalc_score(score);
        if(score < 50){
            scr.setEval_score(Evaluation.ROUGE);
        }
        else {
            scr.setEval_score(Evaluation.VERT);
        }
        return scoreRepository.save(scr);

    }

    public Score sendScore(Long id) {

        Score cnv =scoreRepository.getScore(id);
        return cnv;
    }


}
