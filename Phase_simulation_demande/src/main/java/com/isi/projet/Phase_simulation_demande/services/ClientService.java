package com.isi.projet.Phase_simulation_demande.services;


import com.isi.projet.Phase_simulation_demande.model.Client;
import com.isi.projet.Phase_simulation_demande.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    //@Autowired pour instancier un objet de classe.
    @Autowired
    public ClientRepository convRepository;

    public Client createClient (Client client) {


        return convRepository.save(client);
    }
    //READ
    public List<Client> getClient() {
        return convRepository.findAll();
    }

    //DELETE
    public void deleteClient(Long clientId) {
        convRepository.deleteById(clientId);
    }

    // UPDATE

    public Client updateClient(Long clientId, Client client) {
        Client c = convRepository.findById(clientId).get();
        c.setNom(client.getNom());
        c.setPrenom(client.getPrenom());
        c.setCin(client.getCin());
        c.setSalaire_mensuel(client.getSalaire_mensuel());
        c.setType_contrat(client.getType_contrat());
        c.setDate_naissance(client.getDate_naissance());
        return convRepository.save(c);
    }

}
