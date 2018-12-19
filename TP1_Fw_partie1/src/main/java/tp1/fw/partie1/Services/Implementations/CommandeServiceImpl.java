package tp1.fw.partie1.Services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp1.fw.partie1.Domain.Client;
import tp1.fw.partie1.Domain.Commande;
import tp1.fw.partie1.Domain.Ligne_commande;
import tp1.fw.partie1.Domain.Panier;
import tp1.fw.partie1.Repositories.ClientRepository;
import tp1.fw.partie1.Repositories.CommandeRepository;
import tp1.fw.partie1.Repositories.Ligne_commandeRepository;
import tp1.fw.partie1.Repositories.PanierRepository;
import tp1.fw.partie1.Services.Interfaces.ICommandeService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CommandeServiceImpl implements ICommandeService {

    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    Ligne_commandeRepository ligne_commandeRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    PanierRepository panierRepository;

    @Override
    @Transactional
    public List<Commande> getAllCommandes(){

        List<Commande> result = new ArrayList<>();
        Iterable<Commande> iter= commandeRepository.findAll();
        for (Commande c : iter) {
            result.add(c);
        }
        return result;
    }

    @Override
    @Transactional
    public void validerPanier(String idPanier,String idClient){
        Panier panierAValider=panierRepository.findById(idPanier).get();
        Client client= clientRepository.findById(idClient).get();

        panierAValider.setClient(client);
        client.setPanier(panierAValider);

        Commande commande=new Commande(
                UUID.randomUUID().toString(),
                new Date().toString(),
                panierAValider.getTotalPanier(),
                client,
                panierAValider.getLigne_commandes()
        );
        commandeRepository.save(commande);

        for (Ligne_commande lc:panierAValider.getLigne_commandes()){
            lc.setCommande(commande);
            ligne_commandeRepository.save(lc);
        }

        client.getCommandes().add(commande);

        clientRepository.save(client);
        panierRepository.save(panierAValider);

    }


}
