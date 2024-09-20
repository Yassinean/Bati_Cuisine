import Repository.Implementation.ClientImp;
import Model.Client;
import View.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MainOeuvreGUI mainOeuvreGUI = new MainOeuvreGUI();
        MateriauxGUI materiauxGUI = new MateriauxGUI();
        ProjetGUI projetGUI = new ProjetGUI();
        ClientGUI clientGUI = new ClientGUI();
        MainGUI mainGUI = new MainGUI(clientGUI, projetGUI);

        mainGUI.displayMainMenu();
    }
}
