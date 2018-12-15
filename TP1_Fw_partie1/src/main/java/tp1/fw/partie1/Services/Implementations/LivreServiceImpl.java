package tp1.fw.partie1.Services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tp1.fw.partie1.Domain.Livre;
import tp1.fw.partie1.Domain.Models.ModelNouveauLivre;
import tp1.fw.partie1.Repositories.LivreRepository;
import tp1.fw.partie1.Services.Interfaces.ILivreService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class LivreServiceImpl implements ILivreService {

    @Autowired
    LivreRepository livreRepository;

    @Transactional
    public List<Livre> getAllLivres() {
        List<Livre> result = new ArrayList<>();
        Iterable<Livre> iter= livreRepository.findAll();
        for (Livre l : iter) {
            result.add(l);
        }
        return result;
    }

    @Transactional
    public Livre addLivre(ModelNouveauLivre nouveauLivre) {

        Livre bookToBeSaved=new Livre(nouveauLivre.getIdLivre(),
                nouveauLivre.getTitre(),
                nouveauLivre.getDescription(),
                nouveauLivre.getPrix(),
                nouveauLivre.getNbExemplaire(),
                nouveauLivre.getEnPromotion(),
                null,
                null);
        livreRepository.save(bookToBeSaved);

        return bookToBeSaved;
    }

    @Override
    public List<Livre> findAllByNbExemplaireIsGreaterThanEqual(int nb) {
        List<Livre> list= livreRepository.findAllByNbExemplaireIsGreaterThanEqual(nb);

        return list;
    }

    @Override
    public Livre findById(String id) {

        Livre livre= livreRepository.findById(id).get();
        return livre;
    }

    public List<Livre> getLivresEnPromotion(){
        return livreRepository.findAllByNbExemplaireIsGreaterThanEqualAndEnPromotionIs(1,true);
    }

}