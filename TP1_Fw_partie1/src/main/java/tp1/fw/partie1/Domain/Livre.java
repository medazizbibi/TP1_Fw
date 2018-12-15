package tp1.fw.partie1.Domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Livre {

    @Id
    private String idLivre;

    private String titre;
    private String description;
    private double prix;
    private int nbExemplaire;
    private Boolean enPromotion=false;

    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Promotion promotion;

    @OneToMany(mappedBy = "livre")
    private List<Ligne_commande> ligne_commandes;



}

