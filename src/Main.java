
import Config.DbConfig;
import Repository.Implementation.ClientImp;
import Repository.Implementation.MainOeuvreImp;
import Repository.Implementation.MateriauxImp;
import Repository.Interface.IClient;
import Repository.Interface.IMainOeuvre;
import Repository.Interface.IMateriaux;
import Service.Implementation.ClientServiceImp;
import Service.Implementation.MainOeuvreServiceImp;
import Service.Implementation.MateriauxServiceImp;
import Service.Interface.IClientService;
import Service.Interface.IMainOeuvreService;
import Service.Interface.IMateriauxService;
import View.*;

import java.sql.Connection;
import java.sql.SQLException;


public class Main {

    

    public static void main(String[] args) throws SQLException {
        Connection connection = DbConfig.getInstance().getConnection();

        IClient clientRepository = new ClientImp(connection);
        IClientService clientService = new ClientServiceImp(clientRepository);
        IMainOeuvre mainOeuvreRepository = new MainOeuvreImp();
        IMainOeuvreService mainOeuvreService = new MainOeuvreServiceImp(mainOeuvreRepository);
        IMateriaux materiauxRepository = new MateriauxImp();
        IMateriauxService materiauxService = new MateriauxServiceImp(materiauxRepository);

        MainOeuvreGUI mainOeuvreGUI = new MainOeuvreGUI(mainOeuvreService);
        MateriauxGUI materiauxGUI = new MateriauxGUI(materiauxService);

        ProjetGUI projetGUI = new ProjetGUI();

        ClientGUI clientGUI = new ClientGUI(clientService, projetGUI);


        MainGUI mainGUI = new MainGUI(clientGUI, projetGUI,materiauxGUI,mainOeuvreGUI);

        mainGUI.displayMainMenu();
    }
}

