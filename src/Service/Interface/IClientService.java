package Service.Interface;

import Model.Client;

import java.util.List;

public interface IClientService {
    Client getClientById(Integer id);
    Client getClientByName(String name);
    List<Client> getAllClients();
    void createClient(Client client);
}
