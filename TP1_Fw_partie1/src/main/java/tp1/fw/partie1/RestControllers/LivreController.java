package tp1.fw.partie1.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tp1.fw.partie1.Domain.Livre;
import tp1.fw.partie1.Domain.Models.ModelNouveauLivre;
import tp1.fw.partie1.Services.Interfaces.ILivreService;

import java.util.List;


@RestController
@RequestMapping("/livre")
public class LivreController {


    @Autowired
    ILivreService livreService;

    /**
     * Permet de retourner tous les livres présents dans la BD
     * @return Liste de tous les livres
     */
    @GetMapping("/all")
    public ResponseEntity<List<Livre>> getAllLivres(){

        List<Livre> list=livreService.getAllLivres();
        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    /**
     * Permet de retourner la liste de tous les livres disponibles, c'est à dire les livres
     * dont le nbExemplaire est supérieur ou égal à 1
     * @return Liste de Livre
     */
    @GetMapping("/available")
    public ResponseEntity<List<Livre>> getAllAvailable(){

        List<Livre> list=livreService.findAllByNbExemplaireIsGreaterThanEqual(1);

        return new ResponseEntity<>(list,HttpStatus.OK);
    }


    /**
     * Permet de retourner la liste de tous les livres qui sont en promotion
     * @return Retourne une liste de Livre
     */
    @GetMapping("/enPromotion")
    public ResponseEntity<List<Livre>> getAllEnPromotion(){

        List<Livre> list=livreService.getLivresEnPromotion();

        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    /**
     * Permet d'ajouter un nouveau Livre à la base de données
     * @param modelNouveauLivre Objet contenant des détails sur le livre à ajouter
     * @return Retourne l'objet nouvellement ajouté
     */
    @PostMapping
    public ResponseEntity<Livre> addLivre(@RequestBody ModelNouveauLivre modelNouveauLivre){

        Livre livre= livreService.addLivre(modelNouveauLivre);

        return new ResponseEntity<>(livre,HttpStatus.OK);
    }

    /**
     * Permet de modifier un Livre
     * @param livre le livre à modifier
     * @return Retourne l'objet après la modification
     */
    @PutMapping
    public ResponseEntity<Livre> editLivre(@RequestBody Livre livre){

        Livre livreUpdated= livreService.editLivre(livre);

        return new ResponseEntity<>(livreUpdated,HttpStatus.OK);
    }


}
