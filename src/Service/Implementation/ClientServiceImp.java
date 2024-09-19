package Service.Implementation;

import Model.Client;
import Repository.Interface.IClient;
import Service.Interface.IClientService;

import java.util.List;

public class ClientServiceImp implements IClientService {

    private IClient clientReposInterface;

    private ClientServiceImp(IClient clientReposInterface){
        this.clientReposInterface = clientReposInterface;
    }

    @Override
    public Client getClientById(Integer id) {
        return clientReposInterface.findClientById(id);
    }

    @Override
    public Client getClientByName(String name){
        return clientReposInterface.findClientByName(name);
    }

    @Override
    public List<Client> getAllClients() {
        return clientReposInterface.findAllClients();
    }

    @Override
    public void createClient(Client client) {
        clientReposInterface.addClient(client);
    }
}
