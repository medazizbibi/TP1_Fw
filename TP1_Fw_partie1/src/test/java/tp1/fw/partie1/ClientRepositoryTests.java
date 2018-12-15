package tp1.fw.partie1;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tp1.fw.partie1.Domain.Client;
import tp1.fw.partie1.Repositories.ClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
public class ClientRepositoryTests {
    private static final Logger log = LoggerFactory.getLogger(ClientRepositoryTests.class);

    @Autowired
    TestEntityManager entityManager; /* The TestEntityManager provided by Spring Boot
                                        is an alternative to the standard JPA EntityManager that provides
                                        methods commonly used when writing tests.*/

    @Autowired
    ClientRepository clientRepository;

    @Test
    public void testCreateAndFind() {
        //Given
        Client client1= new Client("CL1","Bibi","Mohamed Aziz","Tunis", 23321757,null,null);
        Client client3= new Client("CL3","Bibi","Moshen","Tunis", 23321757,null,null);
        entityManager.persist(client1);
        entityManager.persist(client3);
        log.info("Ajout des clients dans la base de données");
        //When
        Client found=clientRepository.findById("CL3").get();
        log.info("Récupération d'un objet de la base de données");
        //Then
        assertThat(client3).isEqualTo(found);
    }
    @Test
    public void testFindAllByAdressEqualsTo() {
        //Given
        Client client1= new Client("CL1","Bibi","Mohamed Aziz","Tunis", 23321757,null,null);
        Client client2= new Client("CL2","Bibi","Moshen","Sfax", 23321757,null,null);
        Client client3= new Client("CL3","Bibi","Ilhem","Monastir", 23321757,null,null);
        Client client4= new Client("CL4","Bibi","Lotfi","Tunis", 23321757,null,null);

        entityManager.persist(client1);
        entityManager.persist(client2);
        entityManager.persist(client3);
        entityManager.persist(client4);
        log.info("Ajout des clients dans la base de données");


        //When
        List<Client> found=clientRepository.findAllByAdresseEqualsOrderByNom("Tunis");
        log.info("Récupération d'un objet de la base de données");
        //Then
        assertThat(found).hasSize(2)
                .contains(client1,client4)
                .doesNotContain(client2,client3);
    }

    @Test
    public void testFindAllByTelephoneBetween() {
        //Given
        Client client1= new Client("CL1","Bibi","Mohamed Aziz","Tunis", 23321757,null,null);
        Client client2= new Client("CL2","Bibi","Moshen","Sfax", 55321757,null,null);
        Client client3= new Client("CL3","Bibi","Ilhem","Monastir", 50665982,null,null);
        Client client4= new Client("CL4","Bibi","Lotfi","Tunis", 52002541,null,null);

        entityManager.persist(client1);
        entityManager.persist(client2);
        entityManager.persist(client3);
        entityManager.persist(client4);
        log.info("Ajout des clients dans la base de données");


        //When
        List<Client> orangeClients =clientRepository.findAllByTelephoneIsBetween(50000000,55999999);
        log.info("Récupération d'un objet de la base de données");
        //Then
        assertThat(orangeClients).hasSize(3)
                .contains(client2,client3,client4)
                .doesNotContain(client1);
    }

    @Test
    public void testFindClientsByNomIsStartingWith() {
        //Given
        Client client1= new Client("CL1","Bibi","Mohamed Aziz","Tunis", 23321757,null,null);
        Client client2= new Client("CL2","Karim","Moshen","Sfax", 23321757,null,null);
        Client client3= new Client("CL3","Belarbi","Ilhem","Monastir", 23321757,null,null);
        Client client4= new Client("CL4","Mhiri","Lotfi","Tunis", 23321757,null,null);
        Client client5= new Client("CL5","Bayrem","Lotfi","Tunis", 23321757,null,null);

        entityManager.persist(client1);
        entityManager.persist(client2);
        entityManager.persist(client3);
        entityManager.persist(client4);
        entityManager.persist(client5);

        log.info("Ajout des clients dans la base de données");


        //When
        List<Client> found=clientRepository.findClientByNomIsStartingWithOrderByNom("B");
        log.info("Récupération d'un objet de la base de données");
        //Then
        assertThat(found).hasSize(3)
                .contains(client1,client3,client5)
                .startsWith(client5)
                .endsWith(client1)
                .doesNotContain(client2,client4);
    }

}
