package View;

import Service.Interface.IProjetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ProjetGUI {
    private static final Logger logger = LoggerFactory.getLogger(ProjetGUI.class);
    private IProjetService projetService;
    private Scanner scanner = new Scanner(System.in);

    public ProjetGUI(IProjetService projetService) {
        this.projetService = projetService;
    }

    public void displayMenuProject() {
        System.out.println("=== Menu Principal ===");
        System.out.println("1. Créer un nouveau projet");
        System.out.println("2. Afficher les projets existants");
        System.out.println("3. Calculer le coût d'un projet");
        System.out.println("4. Quitter");

        System.out.println("Choisissez un option : ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                creerProjet();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }

    }
        public void creerProjet() {
            System.out.println("--- Création d'un Nouveau Projet ---");
            System.out.println("Entrez le nom du projet : ");
            String nomProject = scanner.nextLine();
            System.out.println("Entrez la surface de la cuisine (en m²) :");
            double surface = scanner.nextDouble();

            logger.info("Matériau ajouté avec succès !");

        }
}
