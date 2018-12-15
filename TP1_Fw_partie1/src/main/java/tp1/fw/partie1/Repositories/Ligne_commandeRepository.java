package tp1.fw.partie1.Repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tp1.fw.partie1.Domain.Ligne_commande;

@Repository
public interface Ligne_commandeRepository extends CrudRepository<Ligne_commande,String> {
}
