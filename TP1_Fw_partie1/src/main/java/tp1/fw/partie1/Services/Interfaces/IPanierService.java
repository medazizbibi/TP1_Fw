package tp1.fw.partie1.Services.Interfaces;

import org.springframework.http.ResponseEntity;
import tp1.fw.partie1.Domain.Livre;
import tp1.fw.partie1.Domain.Panier;

import java.util.List;

public interface IPanierService {

    public Panier addToPanier(String idPanier,String idLivre,int quantite);
    public Panier addPanier(String idClient);
    public Panier getPanierById(String idPanier);
    public Panier findPanierByClientIdClient(String idClient);

}
