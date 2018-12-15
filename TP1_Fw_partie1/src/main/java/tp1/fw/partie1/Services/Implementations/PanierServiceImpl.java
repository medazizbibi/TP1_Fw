package tp1.fw.partie1.Services.Implementations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tp1.fw.partie1.Domain.*;
import tp1.fw.partie1.Repositories.*;
import tp1.fw.partie1.Services.Interfaces.IPanierService;

import java.util.Date;
import java.util.UUID;

@Service
public class PanierServiceImpl implements IPanierService {

    @Autowired
    PanierRepository panierRepository;

    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    LivreRepository livreRepository;

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    Ligne_commandeRepository ligne_commandeRepository;

    public Panier addToPanier(String idPanier,String idLivre,int quantite){

        //Extraction des entités de la base de données
        Livre livreToBeAdded= livreRepository.findById(idLivre).get();
        Panier panierUsed= panierRepository.findById(idPanier).get();

        //Test sur le nombre d'exemplaire disponibles
        if( quantite> livreToBeAdded.getNbExemplaire()){
            quantite= livreToBeAdded.getNbExemplaire();
        }

        //Ajout d'une nouvelle ligne de commande
        if (livreToBeAdded != null && panierUsed!= null){
            Ligne_commande ligne_commandeAdded=
                    new Ligne_commande(
                            UUID.randomUUID().toString(),
                            quantite,
                            quantite*livreToBeAdded.getPrix(),
                            panierUsed,
                            null,
                            livreToBeAdded);
            ligne_commandeRepository.save(ligne_commandeAdded);
            livreToBeAdded.setNbExemplaire(livreToBeAdded.getNbExemplaire()-quantite);
            livreRepository.save(livreToBeAdded);

            panierUsed.setTotalPanier(panierUsed.getTotalPanier()+quantite*livreToBeAdded.getPrix());
            panierUsed.setNbElements(panierUsed.getNbElements()+1);
            panierRepository.save(panierUsed);
        }

        return panierUsed;
    }

    public Panier addPanier(String idClient){

        Client client = clientRepository.findById(idClient).get();
        Panier panierToBeAdded= new Panier(UUID.randomUUID().toString(),
                                            0,
                                            0,
                                            client,
                                            null);

        panierRepository.save(panierToBeAdded);

        return panierToBeAdded;

    }


    public Panier getPanierById(String idPanier)
    {
        Panier panierFromDb=panierRepository.findById(idPanier).get();
        return panierFromDb;
    }

    public Panier findPanierByClientIdClient(String idClient){
        Panier panier = panierRepository.findPanierByClientIdClient(idClient);
        return panier;
    }


}