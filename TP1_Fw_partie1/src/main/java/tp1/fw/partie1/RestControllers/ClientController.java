package tp1.fw.partie1.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp1.fw.partie1.Domain.Client;
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

    @GetMapping("/all")
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }




}
