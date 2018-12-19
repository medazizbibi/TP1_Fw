package tp1.fw.partie1.Services.Interfaces;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tp1.fw.partie1.Domain.Commande;

import java.util.List;

public interface ICommandeService {

    /**
     * Permet de retourner la liste de toutes les commandes
     * @return Liste de Commande
     */
    List<Commande> getAllCommandes();

    /**
     * Permet de valider un panier par le client. C'est à dire, la confirmation des achats faits ce qui va créer une
     * commande au nom du client concerné et la diminution des livres du stock et par la suite la destruction du panier.
     * @param idPanier Identifiant du panier
     * @param idClient Identifiant du client
     */
    public void validerPanier(String idPanier,String idClient);
    }
