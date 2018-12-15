package tp1.fw.partie1.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {

    @Id
    private String idClient;

    private String nom;
    private String prenom;
    private String adresse;
    private int telephone;

    @OneToOne(mappedBy = "client")
    Panier panier;

    @OneToMany(mappedBy = "client")
    List<Commande> commandes;



}
