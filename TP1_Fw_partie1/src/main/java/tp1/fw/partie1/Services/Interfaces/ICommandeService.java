package tp1.fw.partie1.Services.Interfaces;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tp1.fw.partie1.Domain.Commande;

import java.util.List;

public interface ICommandeService {

    List<Commande> getAllCommandes();
    public void validerPanier(String idPanier,String idClient);
    }
