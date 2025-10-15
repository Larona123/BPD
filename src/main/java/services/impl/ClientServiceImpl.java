package services.impl;

import entity.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.ClientRepository;
import services.intf.ClientServiceIntf;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientServiceIntf {

    private final ClientRepository clientRepository;

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> getById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
