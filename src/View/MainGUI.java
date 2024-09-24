package View;

import Model.Projet;

import java.util.Scanner;

public class MainGUI {
    private ClientGUI clientGUI;
    private ProjetGUI projetGUI;
    private MateriauxGUI materiauxGUI;
    private MainOeuvreGUI mainOeuvreGUI;

    public MainGUI(ClientGUI clientGUI, ProjetGUI projetGUI , MateriauxGUI materiauxGUI , MainOeuvreGUI mainOeuvreGUI) {
        this.clientGUI = clientGUI;
        this.projetGUI = projetGUI;
        this.materiauxGUI = materiauxGUI;
        this.mainOeuvreGUI = mainOeuvreGUI;
    }

    public void displayMainMenu() {
        Projet projet = new Projet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Créer un nouveau projet");
            System.out.println("2. Afficher les projets existants");
            System.out.println("3. Calculer le coût d'un projet");
            System.out.println("4. Quitter");

            System.out.println("Choisissez une option : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    ClientGUI.displayMenuClient();
                    break;
                case 2:
                    projetGUI.displayAllProjects();
                    break;
                case 3:
                    projetGUI.projectTotalCost();
                    break;
                case 4:
                    System.out.println("Quitter l'application.");
                    return;
                default:
                    System.out.println("Choix invalide, réessayez.");
            }
        }
    }
}
