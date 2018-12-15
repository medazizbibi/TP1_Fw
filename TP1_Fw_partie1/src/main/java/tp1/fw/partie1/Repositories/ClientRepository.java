package tp1.fw.partie1.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import tp1.fw.partie1.Domain.Client;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client,String> {

    List<Client> findAllByAdresseEqualsOrderByNom(String adresse);
    List<Client> findAllByTelephoneIsBetween(int inf, int sup);
    List<Client> findClientByNomIsStartingWithOrderByNom(String str);
}
