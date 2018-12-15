package tp1.fw.partie1.Domain;


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
    private Panier panier;

    @ManyToOne
    private Commande commande;

    @ManyToOne
    private Livre livre;

 }
