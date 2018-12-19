package tp1.fw.partie1.RestControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp1.fw.partie1.Domain.Commande;
import tp1.fw.partie1.Domain.Panier;
import tp1.fw.partie1.Services.Interfaces.ICommandeService;
import tp1.fw.partie1.Services.Interfaces.IPanierService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/commande")
public class CommandeController {

    @Autowired
    IPanierService panierService;

    @Autowired
    ICommandeService commandeService;

    /**
     * Permet de valider un panier par le client. C'est à dire, la confirmation des achats faits ce qui va créer une
     * commande au nom du client concerné et la diminution des livres du stock et par la suite la destruction du panier.
     * @param idPanier Identifiant du panier
     * @param idClient Identifiant du client
     */
    @PostMapping("/valider/{idPanier}/client/{idClient}")
    public void  passerCommandeApresValidationPanier(@PathVariable String idPanier,
                                                      @PathVariable String idClient){

        commandeService.validerPanier(idPanier,idClient);

    }

    /**
     * Permet de retourner la liste de toutes les commandes
     * @return Liste de Commande
     */
    @GetMapping("/all")
    public ResponseEntity<List<Commande>> getAllCommandes(){

        List<Commande> result= commandeService.getAllCommandes();
        return new ResponseEntity<>(result, HttpStatus.OK);

    }



}

