package tp1.fw.partie1.Domain;


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
public class Commande {

    @Id
    private String idCommande;

    private String date;
    private double montant;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "commande")
    private List<Ligne_commande> ligne_commandes;


}
