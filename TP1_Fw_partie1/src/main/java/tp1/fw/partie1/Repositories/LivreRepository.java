package tp1.fw.partie1.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import tp1.fw.partie1.Domain.Livre;

import java.util.List;

@Repository
public interface LivreRepository extends CrudRepository<Livre,String> {

    List<Livre> findAllByPrixBetween(double prix1,double prix2);
    List<Livre> findAllByPrixIsGreaterThan(double prix1);
    List<Livre> findAllByPrixIsLessThan(double prix1);
    List<Livre> findAllByTitreEqualsOrderByPrix(String titre);
    List<Livre> findAllByNbExemplaireIsGreaterThanEqual(int nb);
    List<Livre> findAllByNbExemplaireIsGreaterThanEqualAndEnPromotionIs(int nb,Boolean bool);


}
