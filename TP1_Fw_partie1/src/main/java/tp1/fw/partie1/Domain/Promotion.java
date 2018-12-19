package tp1.fw.partie1.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Promotion {

    @Id
    private String idPromotion;

    private String dateDebut;
    private int dureeEnJours;
    private double pourcentage;

    @OneToOne
    @JsonBackReference
    private Livre livre;
}
