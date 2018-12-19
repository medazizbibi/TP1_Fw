package tp1.fw.partie1.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp1.fw.partie1.Domain.Client;
import tp1.fw.partie1.Domain.Models.ModelClient;
import tp1.fw.partie1.Domain.Panier;
import tp1.fw.partie1.Repositories.ClientRepository;
import tp1.fw.partie1.Services.Interfaces.IClientService;
import tp1.fw.partie1.Services.Interfaces.IPanierService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    IClientService clientService;

    @Autowired
    IPanierService panierService;

    /**
     * Permet de retourner la liste de tous les clients présents dans la base de données
     * @return Liste de tous les clients
     */
    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients(){

        List<Client> list=clientService.getAllClients();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Permet d'ajouter un nouveau client dans la base de données
     * @param modelClient Objet contenant les détails du client à ajouter
     * @return retourner le client nouvellement ajouté
     */
    @PostMapping
    public ResponseEntity<Client> addClientToDb(@RequestBody ModelClient modelClient){

            Client client = clientService.addClient(modelClient);
            return new ResponseEntity<>(client,HttpStatus.CREATED);

    }

    /**
     * Permet de modifier un client présent dans la base de données
     * @param client Le client avec les nouveaux champs modifiés
     * @return Le client après la modification
     */
    @PutMapping
    public ResponseEntity<Client> editClient(@RequestBody Client client){

        Client clientEdited = clientService.editClient(client);
        return new ResponseEntity<>(clientEdited,HttpStatus.OK);

    }

    /**
     * Permet de supprimer un client de la base de données
     * @param id Identifiant du client à supprimer
     */
    @DeleteMapping("/{id}")
    public void editClient(@PathVariable String id){

        clientService.deleteClient(id);

    }




}
