package tp1.fw.partie1.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp1.fw.partie1.Domain.Models.ModelPromotion;
import tp1.fw.partie1.Domain.Promotion;
import tp1.fw.partie1.Services.Interfaces.IPromotionService;

import java.util.List;

@RestController
@RequestMapping("/promotion")
public class PromotionController {


    @Autowired
    IPromotionService promotionService;

    /**
     * Permet de retourner la liste de toutes les promotions présentes dans la base de données
     * @return Liste de toutes les promotions
     */
    @GetMapping("/all")
    public ResponseEntity<List<Promotion>> getAllPromotions(){

        List<Promotion> list=promotionService.getAllPromotion();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Permet d'ajouter une nouvelle promotion dans la base de données
     * @param modelPromotion Objet contenant les détails de la promotion à ajouter
     * @return retourner la promotion nouvellement ajoutée
     */
    @PostMapping
    public ResponseEntity<Promotion> addPromotionToDb(@RequestBody ModelPromotion modelPromotion){

        Promotion promotion = promotionService.addPromotion(modelPromotion);
        return new ResponseEntity<>(promotion,HttpStatus.CREATED);

    }

    /**
     * Permet de modifier une promotion présente dans la base de données
     * @param promotion La promotion avec les nouveaux champs modifiés
     * @return La promotion après la modification
     */
    @PutMapping
    public ResponseEntity<Promotion> editPromotion(@RequestBody Promotion promotion){

        Promotion promotionEdited = promotionService.editPromotion(promotion);
        return new ResponseEntity<>(promotionEdited,HttpStatus.OK);

    }

    /**
     * Permet de supprimer un client de la base de données
     * @param id Identifiant du client à supprimer
     */
    @DeleteMapping("/{id}")
    public void editPromotion(@PathVariable String id){

        promotionService.deletePromotion(id);

    }


}
