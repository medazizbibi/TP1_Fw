package tp1.fw.partie1.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import tp1.fw.partie1.Domain.Livre;
import tp1.fw.partie1.Domain.Panier;

import java.util.List;

@Repository
public interface PanierRepository extends CrudRepository<Panier,String> {

    List<Panier> findAllByTotalPanierGreaterThanEqual(double montant);
    List<Panier> findAllByTotalPanierEquals(double montant);
    List<Panier> findAllByNbElementsIsGreaterThanEqualAndTotalPanierIsGreaterThanEqual(int nb, double montant);
    Panier findPanierByClientIdClient(String idClient);

}
