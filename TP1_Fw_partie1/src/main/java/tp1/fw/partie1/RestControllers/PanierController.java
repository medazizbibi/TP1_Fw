package tp1.fw.partie1.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tp1.fw.partie1.Domain.Livre;
import tp1.fw.partie1.Domain.Models.ModelLivre;
import tp1.fw.partie1.Domain.Panier;
import tp1.fw.partie1.Services.Interfaces.ILivreService;
import tp1.fw.partie1.Services.Interfaces.IPanierService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/panier")
public class PanierController {

    @Autowired
    IPanierService panierService;

    @Autowired
    ILivreService livreService;


    /**
     * Permet de créer un panier et de l'associer au visiteur courant
     * @param idClient Identifiant du visiteur
     * @return Retourne le panier nouvellement crée
     */
    @PostMapping(value="/visiteur/{idClient}")
    public ResponseEntity<Panier> createPanier(@PathVariable String idClient){

        //Création d'un panier associé au client visiteur
        Panier nouveau_panier= panierService.addPanier(idClient);
        return new ResponseEntity<>(nouveau_panier,HttpStatus.OK);

    }

    /**
     * Permet de retourner le panier d'un visiteur bien déterminé
     * @param idClient Idenifiant du visiteur
     * @return Retourne un Panier
     */
    @GetMapping(value="/visiteur/{idClient}")
    public ResponseEntity<Panier> getPanierByClientId(@PathVariable String idClient){

        //Retour d'un panier associé au client visiteur
        Panier panier= panierService.findPanierByClientIdClient(idClient);
        return new ResponseEntity<>(panier,HttpStatus.OK);

    }

    /**
     * Permet de retourner un panier entier
     * @param id : Identifiant du panier en question
     * @return Retourne un ResponseEntity du panier
     */
    @GetMapping("/{id}")
    public ResponseEntity<Panier> getPanier(@PathVariable String id){

        Panier panierToBeReturned = panierService.getPanierById(id);
        return  new ResponseEntity<Panier>(panierToBeReturned,HttpStatus.OK);
    }

    /**
     * Permet d'ajouter un livre choisi par le client à un panier
     * @param idPanier Identifiant du panier en question
     * @param modelLivre Model contenant l'identifiant du livre ainsi que la quantité voulue
     * @return retourne un ResponseEntity du panier
     */
    @PutMapping("/ajouterAuPanier/{idPanier}")
    public void ajouterAuPanier(@PathVariable String idPanier,
                                            @RequestBody ModelLivre modelLivre){



        Panier panier=panierService.addToPanier(idPanier,
                                                modelLivre.getIdLivre(),
                                                modelLivre.getQuantite());

    }
}
