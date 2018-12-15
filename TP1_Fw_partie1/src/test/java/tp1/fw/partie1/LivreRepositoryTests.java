package tp1.fw.partie1;

import org.assertj.core.groups.Tuple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import tp1.fw.partie1.Domain.Livre;
import tp1.fw.partie1.Repositories.ClientRepository;
import tp1.fw.partie1.Repositories.LivreRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
public class LivreRepositoryTests {
    private static final Logger log = LoggerFactory.getLogger(ClientRepositoryTests.class);

    @Autowired
    TestEntityManager entityManager; /* The TestEntityManager provided by Spring Boot
                                        is an alternative to the standard JPA EntityManager that provides
                                        methods commonly used when writing tests.*/

    @Autowired
    LivreRepository livreRepository;

    @Test
    public void testCreateAndFind(){

        //Given
        Livre livre1= new Livre("L1","Titre1","Description1",34.500,10,false,null,null);
        Livre livre2= new Livre("L2","Titre2","Description2",25.600,12,false,null,null);
        Livre livre3= new Livre("L3","Titre3","Description3",31.200,15,false,null,null);
        Livre livre4= new Livre("L4","Titre4","Description4",16.500,5,false,null,null);

        entityManager.persist(livre1);
        entityManager.persist(livre2);
        entityManager.persist(livre3);
        entityManager.persist(livre4);

        log.info("Ajout des livres dans la base de données");

        Livre found=livreRepository.findById("L1").get();
        log.info("Récupération d'un objet de la base de données");


        //Then
        assertThat(livre1).describedAs("Le livre recherché ! ").isEqualTo(found);
        assertThat(livre2).describedAs("Le livre Titre2").isNotEqualTo(found);

    }

    @Test
    public void testPrixBetween()
    {
        Livre livre1= new Livre("L1","Titre1","Description1",34.500,10,false,null,null);
        Livre livre2= new Livre("L2","Titre2","Description2",25.600,12,false,null,null);
        Livre livre3= new Livre("L3","Titre3","Description3",31.200,15,false,null,null);
        Livre livre4= new Livre("L4","Titre4","Description4",16.500,5,false,null,null);

        entityManager.persist(livre1);
        entityManager.persist(livre2);
        entityManager.persist(livre3);
        entityManager.persist(livre4);

        log.info("Ajout des livres dans la base de données");

        List<Livre> listeLivres= livreRepository.findAllByPrixBetween(28.000,35.000);
        List<Livre> expected= new ArrayList<>();
        expected.add(livre1);
        expected.add(livre3);

        assertThat(listeLivres).hasSize(2)
                .containsExactlyInAnyOrder(livre1,livre3)
                .isSameAs(expected)
                .isNotNull();
    }

    @Test
    public void testPrixGreaterThan()
    {
        Livre livre1= new Livre("L1","Titre1","Description1",34.500,10,false,null,null);
        Livre livre2= new Livre("L2","Titre2","Description2",25.600,12,false,null,null);
        Livre livre3= new Livre("L3","Titre3","Description3",31.200,15,false,null,null);
        Livre livre4= new Livre("L4","Titre4","Description4",16.500,5,false,null,null);

        entityManager.persist(livre1);
        entityManager.persist(livre2);
        entityManager.persist(livre3);
        entityManager.persist(livre4);

        log.info("Ajout des livres dans la base de données");

        List<Livre> listeLivres= livreRepository.findAllByPrixIsGreaterThan(25.000);

        assertThat(listeLivres).hasSize(3)
                .extracting("titre")
                .contains("Titre1","Titre2","Titre3");
    }

    @Test
    public void testTitreEquals()
    {
        Livre livre1= new Livre("L1","Titre1","Description1",34.500,10,false,null,null);
        Livre livre2= new Livre("L2","Titre2","Description2",25.600,12,false,null,null);
        Livre livre3= new Livre("L3","Titre1","Description3",31.200,15,false,null,null);
        Livre livre4= new Livre("L4","Titre1","Description4",16.500,5,false,null,null);
        Livre livre5= new Livre("L5","Titre1","Description4",56.000,7,false,null,null);


        entityManager.persist(livre1);
        entityManager.persist(livre2);
        entityManager.persist(livre3);
        entityManager.persist(livre4);
        entityManager.persist(livre5);
        log.info("Ajout des livres dans la base de données");

        List<Livre> listeLivres= livreRepository.findAllByTitreEqualsOrderByPrix("Titre1");

        assertThat(listeLivres).as("Liste des livres qui ont pour titre %s","Titre1")
                .hasSize(4)
                .extracting("titre","prix","nbExemplaire")
                .contains(tuple("Titre1",34.500,10),
                        tuple("Titre1",16.500,5),
                        tuple("Titre1",31.200,15),
                        tuple("Titre1",56.000,7));
    }

}
