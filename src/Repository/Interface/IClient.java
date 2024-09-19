package Repository.Interface;

import Model.Client;

import java.util.List;

public interface IClient {
    List<Client> findAllClients();
    Client findClientByName(String name);
    Client findClientById(Integer id);
    void addClient(Client client);
}
