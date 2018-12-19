package tp1.fw.partie1.Services.Interfaces;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tp1.fw.partie1.Domain.Client;
import tp1.fw.partie1.Domain.Models.ModelClient;

import java.util.List;


public interface IClientService {
    /**
     * Permet de retourner la liste de tous les clients présents dans la base de données
     * @return Liste de tous les clients
     */
    List<Client> getAllClients();

    /**
     * Permet d'ajouter un nouveau client dans la base de données
     * @param modelClient Objet contenant les détails du client à ajouter
     * @return retourner le client nouvellement ajouté
     */
    Client addClient(ModelClient modelClient);

    /**
     * Permet de modifier un client présent dans la base de données
     * @param client Le client avec les nouveaux champs modifiés
     * @return Le client après la modification
     */
    Client editClient(Client client);

    /**
     * Permet de supprimer un client de la base de données
     * @param idClient Identifiant du client à supprimer
     */
    void deleteClient(String idClient);
}
