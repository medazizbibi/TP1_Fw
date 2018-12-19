package tp1.fw.partie1.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @JsonBackReference
    @OneToOne(mappedBy = "client",fetch = FetchType.LAZY)
    Panier panier;

    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Commande> commandes;



}
