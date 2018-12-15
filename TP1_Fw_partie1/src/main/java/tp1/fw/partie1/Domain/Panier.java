package tp1.fw.partie1.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Panier {

    @Id
    private String idPanier;

    private int nbElements;
    private double totalPanier ;

    @OneToOne(fetch = FetchType.LAZY)
    private Client client;

    @OneToMany(mappedBy = "panier")
    private List<Ligne_commande> ligne_commandes;


}
