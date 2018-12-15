package tp1.fw.partie1.Domain.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModelNouveauLivre {

    private String idLivre;

    private String titre;
    private String description;
    private double prix;
    private int nbExemplaire;
    private Boolean enPromotion=false;
}
