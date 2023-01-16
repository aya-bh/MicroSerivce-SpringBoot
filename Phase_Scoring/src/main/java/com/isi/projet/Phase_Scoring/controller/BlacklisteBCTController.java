package com.isi.projet.Phase_Scoring.controller;

import com.isi.projet.Phase_Scoring.model.BlacklisteBCT;
import com.isi.projet.Phase_Scoring.services.BlacklisteBCTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BlacklisteBCTController {
    @Autowired
    public BlacklisteBCTService blacklisteBCTService;

    @RequestMapping(value="/blacklistes", method = RequestMethod.POST)
    public BlacklisteBCT createBlacklisteBCT(@RequestBody BlacklisteBCT c) {
        return blacklisteBCTService.createBlacklisteBCT(c);
    }

    @RequestMapping(value="/blacklistes", method=RequestMethod.GET)
    public List<BlacklisteBCT> readBlacklisteBCT() {
        return blacklisteBCTService.getBlacklisteBCT();
    }

    @RequestMapping(value ="/blacklistes/{id}", method=RequestMethod.PUT)
    public BlacklisteBCT updateClient (@PathVariable(value = "id") Long id, @RequestBody BlacklisteBCT bcDetails)
    {
        return blacklisteBCTService.updateBlacklisteBCT(id, bcDetails);
    }

    @RequestMapping(value="/blacklistes/{id}", method=RequestMethod.DELETE)
    public void deleteBlacklisteBCT(@PathVariable(value = "id") Long id) {
        blacklisteBCTService.deleteBlacklisteBCT(id);
    }
}
