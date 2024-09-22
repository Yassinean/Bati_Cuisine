package Service.Implementation;

import Model.Client;
import Repository.Implementation.ClientImp;
import Repository.Interface.IClient;
import Service.Interface.IClientService;


public class ClientServiceImp implements IClientService {

    private IClient clientReposInterface = new ClientImp();

    public ClientServiceImp(IClient clientReposInterface){
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
    public void createClient(Client client) {
        clientReposInterface.addClient(client);
    }
}
