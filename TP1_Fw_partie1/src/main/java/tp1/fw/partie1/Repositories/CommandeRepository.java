package tp1.fw.partie1.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import tp1.fw.partie1.Domain.Commande;

import java.util.List;


@Repository
public interface CommandeRepository extends CrudRepository<Commande,String> {

    List<Commande> findByDateBetween(String date1, String dat2);
    List<Commande> findByMontantGreaterThan(double montant);
}