package com.isi.projet.Phase_Scoring.services;

import com.isi.projet.Phase_Scoring.Repository.BlacklisteBCTRepository;
import com.isi.projet.Phase_Scoring.model.BlacklisteBCT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlacklisteBCTService {
    @Autowired
    public BlacklisteBCTRepository blacklisteBCTRepository;

    public BlacklisteBCT createBlacklisteBCT (BlacklisteBCT b) {

        return blacklisteBCTRepository.save(b);
    }
    //READ
    public List<BlacklisteBCT> getBlacklisteBCT() {
        return blacklisteBCTRepository.findAll();
    }

    //DELETE
    public void deleteBlacklisteBCT(Long bcId) {
        blacklisteBCTRepository.deleteById(bcId);
    }

    // UPDATE

    public BlacklisteBCT updateBlacklisteBCT(Long bctId, BlacklisteBCT bct) {
        BlacklisteBCT c = blacklisteBCTRepository.findById(bctId).get();
        c.setNom(bct.getNom());
        c.setPrenom(bct.getPrenom());
        c.setCin(bct.getCin());

        return blacklisteBCTRepository.save(c);
    }

}
