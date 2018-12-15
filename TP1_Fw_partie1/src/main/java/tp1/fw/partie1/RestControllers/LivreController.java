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

    @GetMapping("/all")
    public ResponseEntity<List<Livre>> getAllLivres(){

        List<Livre> list=livreService.getAllLivres();
        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @GetMapping("/available")
    public ResponseEntity<List<Livre>> getAllAvailable(){

        List<Livre> list=livreService.findAllByNbExemplaireIsGreaterThanEqual(1);

        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/enPromotion")
    public ResponseEntity<List<Livre>> getAllEnPromotion(){

        List<Livre> list=livreService.getLivresEnPromotion();

        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Livre> addLivre(@RequestBody ModelNouveauLivre modelNouveauLivre){

        Livre livre= livreService.addLivre(modelNouveauLivre);

        return new ResponseEntity<>(livre,HttpStatus.OK);
    }

}
