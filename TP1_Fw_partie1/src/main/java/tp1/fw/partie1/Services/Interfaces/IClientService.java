package tp1.fw.partie1.Services.Interfaces;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tp1.fw.partie1.Domain.Client;

import java.util.List;


public interface IClientService {

    List<Client> getAllClients();
}
