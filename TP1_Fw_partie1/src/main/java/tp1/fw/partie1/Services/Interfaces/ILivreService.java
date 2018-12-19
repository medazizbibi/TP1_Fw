package tp1.fw.partie1.Services.Interfaces;


import org.springframework.http.ResponseEntity;
import tp1.fw.partie1.Domain.Livre;
import tp1.fw.partie1.Domain.Models.ModelNouveauLivre;

import java.util.List;

public interface ILivreService {

    List<Livre> getAllLivres();
    List<Livre> getLivresEnPromotion();
    List<Livre> findAllByNbExemplaireIsGreaterThanEqual(int nb);

    Livre addLivre(ModelNouveauLivre nouveauLivre);
    Livre findById(String id);
    Livre editLivre(Livre livre);
}
