package tp1.fw.partie1.Services.Interfaces;


import org.springframework.http.ResponseEntity;
import tp1.fw.partie1.Domain.Livre;
import tp1.fw.partie1.Domain.Models.ModelNouveauLivre;

import java.util.List;

public interface ILivreService {

    List<Livre> getAllLivres();
    Livre addLivre(ModelNouveauLivre nouveauLivre);
    List<Livre> findAllByNbExemplaireIsGreaterThanEqual(int nb);
    Livre findById(String id);
    List<Livre> getLivresEnPromotion();
}
