package services.intf;

import entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientServiceIntf {
    List<Client> getAll();

    Optional<Client> getById(Long id);

    Client save(Client client);

    void delete(Long id);
}
