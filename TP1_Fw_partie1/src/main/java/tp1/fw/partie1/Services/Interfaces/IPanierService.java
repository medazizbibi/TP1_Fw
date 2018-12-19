package tp1.fw.partie1.Services.Interfaces;

import org.springframework.http.ResponseEntity;
import tp1.fw.partie1.Domain.Livre;
import tp1.fw.partie1.Domain.Panier;

import java.util.List;

public interface IPanierService {


    /**
     * Ajout d'un livre à un panier
     * @param idPanier Identifiant du panier
     * @param idLivre identifiant du livre
     * @param quantite quantité du livre
     * @return Retourne le panier en question
     */
    public Panier addToPanier(String idPanier,String idLivre,int quantite);

    /**
     * Ajout d'un panier à un visiteur
     * @param idClient Identifiant du client
     * @return Retourne le panier crée
     */
    public Panier addPanier(String idClient);

    /**
     * Permet d'obtenir un panier en passant son identifiant
     * @param idPanier Identifiant du panier à retourner
     * @return Retourne un panier
     */
    public Panier getPanierById(String idPanier);
    /**
     * Permet de chercher le panier d'un client spécifique
     * @param idClient Identifiant du client
     * @return Retourne un panier
     */
    public Panier findPanierByClientIdClient(String idClient);


}
