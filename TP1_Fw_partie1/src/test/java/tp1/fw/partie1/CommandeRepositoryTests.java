package tp1.fw.partie1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import tp1.fw.partie1.Domain.Commande;
import tp1.fw.partie1.Repositories.CommandeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
public class CommandeRepositoryTests {

    private static final Logger log = LoggerFactory.getLogger(ClientRepositoryTests.class);

    @Autowired
    TestEntityManager entityManager; /* The TestEntityManager provided by Spring Boot
                                        is an alternative to the standard JPA EntityManager that provides
                                        methods commonly used when writing tests.*/

    @Autowired
    CommandeRepository commandeRepository;

    @Test
    public void testCreateAndFind(){

        //Given
        Commande commande1= new Commande("C1","20-11-2018",120.450,null,null);
        Commande commande2= new Commande("C2","21-11-2018",180.65,null,null);
        Commande commande3= new Commande("C3","23-11-2018",120.450,null,null);
        Commande commande4= new Commande("C4","25-11-2018",80.500,null,null);


        entityManager.persist(commande1);
        entityManager.persist(commande2);
        entityManager.persist(commande3);
        entityManager.persist(commande4);

        log.info("Ajout des commandes dans la base de données");
        Commande found=commandeRepository.findById("C3").get();
        log.info("Récupération d'un objet de la base de données");
        //Then
        assertThat(commande3).describedAs("La commande recherchée ! ").isEqualTo(found);
    }

    @Test
    public void testDateBetween(){
        //Given
        Commande commande1= new Commande("C1","20-11-2018",120.450,null,null);
        Commande commande2= new Commande("C2","21-11-2018",180.65,null,null);
        Commande commande3= new Commande("C3","23-11-2018",120.450,null,null);
        Commande commande4= new Commande("C4","25-11-2018",80.500,null,null);

        entityManager.persist(commande1);
        entityManager.persist(commande2);
        entityManager.persist(commande3);
        entityManager.persist(commande4);

        log.info("Ajout des commandes dans la base de données");

        List<Commande> found = commandeRepository.findByDateBetween("21-11-2018","24-11-2018");

        assertThat(found).as("Les commandes effectuées entre le 21 et le 23 novembre 2018")
                .containsExactlyInAnyOrder(commande2,commande3)
                .doesNotContain(commande1,commande4)
                .hasSize(2)
        ;
    }

    @Test
    public void testGreaterThan() {
        //Given
        Commande commande1 = new Commande("C1", "20-11-2018", 120.450,null,null);
        Commande commande2 = new Commande("C2", "21-11-2018", 180.65,null,null);
        Commande commande3 = new Commande("C3", "23-11-2018", 150.22,null,null);
        Commande commande4 = new Commande("C4", "25-11-2018", 80.500,null,null);

        entityManager.persist(commande1);
        entityManager.persist(commande2);
        entityManager.persist(commande3);
        entityManager.persist(commande4);

        log.info("Ajout des commandes dans la base de données");

        List<Commande> found= commandeRepository.findByMontantGreaterThan(90.000);

        assertThat(found).extracting("montant")
                .contains(120.450,180.65,150.22)
                .doesNotContain(80.500);



    }
}
