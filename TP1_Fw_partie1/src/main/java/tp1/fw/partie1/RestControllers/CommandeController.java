package tp1.fw.partie1.RestControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tp1.fw.partie1.Domain.Commande;
import tp1.fw.partie1.Domain.Panier;
import tp1.fw.partie1.Services.Interfaces.ICommandeService;
import tp1.fw.partie1.Services.Interfaces.IPanierService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/commande")
public class CommandeController {

    @Autowired
    IPanierService panierService;

    @Autowired
    ICommandeService commandeService;

//    @PostMapping("/valider/{id}")
//    public String passerCommandeApresValidationPanier(@PathVariable String id, HttpServletRequest request){
//
//        Panier panierFromSession = (Panier)request.getSession().getAttribute("monPanier");
//        if (panierFromSession == null){
//            panierFromSession = panierService.addPanier();
//            request.getSession().setAttribute("monPanier",panierFromSession);
//        }

    @PostMapping("/valider/{idPanier}/client/{idClient}")
    public void  passerCommandeApresValidationPanier(@PathVariable String idPanier,
                                                      @PathVariable String idClient){

        commandeService.validerPanier(idPanier,idClient);

    }



}

