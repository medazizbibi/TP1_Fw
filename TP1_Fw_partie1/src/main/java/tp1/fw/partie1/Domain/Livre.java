package tp1.fw.partie1.Domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private Promotion promotion;

    @OneToMany(mappedBy = "livre")
    @JsonManagedReference
    private List<Ligne_commande> ligne_commandes;



}

