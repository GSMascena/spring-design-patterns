package gsm.springdesignpatterns.service.imp;

import gsm.springdesignpatterns.model.Client;
import gsm.springdesignpatterns.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImp implements ClientService {

    @Override
    public Iterable<Client> findAll() {
        return null;
    }

    @Override
    public Client findById(Long id) {
        return null;
    }

    @Override
    public void put(Client client) {

    }

    @Override
    public void update(Long id, Client client) {

    }

    @Override
    public void delete(Long id) {

    }
}
