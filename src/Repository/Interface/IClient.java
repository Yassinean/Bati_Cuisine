package Repository.Interface;

import Model.Client;

import java.util.List;

public interface IClient {

    Client findClientByName(String name);

    Client findClientById(Integer id);

    Client addClient(Client client);
}
