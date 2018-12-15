package tp1.fw.partie1;


import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import tp1.fw.partie1.Domain.Panier;
import tp1.fw.partie1.Repositories.PanierRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
public class PanierRepositoryTests {

    private static final Logger log = LoggerFactory.getLogger(ClientRepositoryTests.class);

    @Autowired
    TestEntityManager entityManager; /* The TestEntityManager provided by Spring Boot
                                        is an alternative to the standard JPA EntityManager that provides
                                        methods commonly used when writing tests.*/

    @Autowired
    PanierRepository panierRepository;


    @Test
    public void testCreateAndFind(){

        //Given
        Panier panier1= new Panier("P1",10,120.540,null,null);
        Panier panier2= new Panier("P2",8,160.650,null,null);
        Panier panier3= new Panier("P3",2,44.520,null,null);
        Panier panier4= new Panier("P4",2,88.960,null,null);


        entityManager.persist(panier1);
        entityManager.persist(panier2);
        entityManager.persist(panier3);
        entityManager.persist(panier4);

        log.info("Ajout des commandes dans la base de données");
        Panier found= panierRepository.findById("P1").get();
        log.info("Récupération d'un objet de la base de données");
        //Then
        assertThat(panier1).describedAs("Le panier recherché ! ").isEqualTo(found);
    }

    @Test
    public void testTotalGreaterThan(){

        //Given
        Panier panier1= new Panier("P1",10,120.540,null,null);
        Panier panier2= new Panier("P2",8,160.650,null,null);
        Panier panier3= new Panier("P3",2,44.520,null,null);
        Panier panier4= new Panier("P4",2,88.960,null,null);


        entityManager.persist(panier1);
        entityManager.persist(panier2);
        entityManager.persist(panier3);
        entityManager.persist(panier4);

        log.info("Ajout des commandes dans la base de données");
        List<Panier> found= panierRepository.findAllByTotalPanierGreaterThanEqual(88.960);
        log.info("Récupération d'un objet de la base de données");
        //Then
        assertThat(found).describedAs("Le panier recherché ! ").hasSize(3)
        .containsExactly(panier1,panier2, panier4)
        .doesNotContain(panier3)
        .endsWith(panier4);
    }

    @Test
    public void testTotalPanierEquals(){

        //Given
        Panier panier1= new Panier("P1",10,88.960,null,null);
        Panier panier2= new Panier("P2",8,160.650,null,null);
        Panier panier3= new Panier("P3",2,44.520,null,null);
        Panier panier4= new Panier("P4",2,88.960,null,null);


        entityManager.persist(panier1);
        entityManager.persist(panier2);
        entityManager.persist(panier3);
        entityManager.persist(panier4);

        log.info("Ajout des commandes dans la base de données");
        List<Panier> found= panierRepository.findAllByTotalPanierEquals(88.960);
        log.info("Récupération d'un objet de la base de données");
        //Then

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(found).hasSize(3).startsWith(panier4);

        softly.assertThat(found).containsExactly(panier4,panier3);

        softly.assertThat(found).describedAs("Le panier recherché ! ").hasSize(2)
                .containsExactly(panier1,panier4)
                .doesNotContain(panier3)
                .first()
                .isEqualTo(panier1)
                .extracting("idPanier")
                .contains("P1");
        softly.assertAll();


    }

    @Test
    public void testNbAndTotalGreaterThan(){

        //Given
        Panier panier1= new Panier("P1",10,88.960,null,null);
        Panier panier2= new Panier("P2",8,160.650,null,null);
        Panier panier3= new Panier("P3",6,44.520,null,null);
        Panier panier4= new Panier("P4",2,88.960,null,null);
        entityManager.persist(panier1);
        entityManager.persist(panier2);
        entityManager.persist(panier3);
        entityManager.persist(panier4);

        log.info("Ajout des commandes dans la base de données");
        List<Panier> found= panierRepository.findAllByNbElementsIsGreaterThanEqualAndTotalPanierIsGreaterThanEqual(5,150.00);
        log.info("Récupération d'un objet de la base de données");
        //Then
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(found).hasSize(3);
        softly.assertThat(found).first().isEqualTo(panier3);
        softly.assertThat(found).describedAs("Le panier recherché ! ").hasSize(1)
                .containsExactly(panier2)
                .doesNotContain(panier1,panier3,panier4)
                .extracting("idPanier","nbElements","totalPanier")
                .contains(tuple("P2",8,160.650));

        softly.assertAll();


    }
}
