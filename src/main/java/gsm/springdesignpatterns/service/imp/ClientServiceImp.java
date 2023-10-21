package gsm.springdesignpatterns.service.imp;

import gsm.springdesignpatterns.model.Address;
import gsm.springdesignpatterns.model.AddressRepository;
import gsm.springdesignpatterns.model.Client;
import gsm.springdesignpatterns.model.ClientRepository;
import gsm.springdesignpatterns.service.ClientService;
import gsm.springdesignpatterns.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImp implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ViaCepService viaCepService;
    @Override
    public Iterable<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        Optional<Client> clientById = clientRepository.findById(id);
        return clientById.orElse(null);
    }

    private void verifyAndAddClient(Client client) {
        String cep = client.getAddress().getCep();
        Address AddressByCep = addressRepository.findById(cep).orElseGet(() -> {
            Address newAddress = viaCepService.consultCep(cep);
            addressRepository.save(newAddress);
            return newAddress;
        });
        client.setAddress(AddressByCep);
        clientRepository.save(client);
    }

    @Override
    public void put(Client client) {
        verifyAndAddClient(client);
    }

    @Override
    public void update(Long id, Client client) {
        Optional<Client> clientById = clientRepository.findById(id);
        if (clientById.isPresent()) {
            verifyAndAddClient(client);
        }
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
