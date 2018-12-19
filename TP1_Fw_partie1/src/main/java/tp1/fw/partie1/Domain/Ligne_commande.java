package tp1.fw.partie1.Domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ligne_commande {



    @Id
    private String idLigneCommande;

    private int quantite;
    private double prixVente;

    @ManyToOne
    @JsonBackReference
    private Panier panier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Commande commande;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Livre livre;

 }
