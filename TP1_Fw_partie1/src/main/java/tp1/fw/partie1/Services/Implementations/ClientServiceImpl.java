package tp1.fw.partie1.Services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp1.fw.partie1.Domain.Client;
import tp1.fw.partie1.Repositories.ClientRepository;
import tp1.fw.partie1.Services.Interfaces.IClientService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    ClientRepository clientRepository;

    @Transactional
    public List<Client> getAllClients() {
        List<Client> result = new ArrayList<>();
        Iterable<Client> iter= clientRepository.findAll();
        for (Client c : iter) {
            result.add(c);
        }
        return result;
    }
}
