package View;

import Model.Composant;
import Model.Enum.MainOeuvreType;
import Model.MainOeuvre;
import Model.Materiaux;
import Model.Projet;
import Service.Implementation.MainOeuvreServiceImp;
import Service.Interface.IMainOeuvreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainOeuvreGUI {
    private static final Logger logger = LoggerFactory.getLogger(MainOeuvreGUI.class);
    private static IMainOeuvreService mainOeuvre = new MainOeuvreServiceImp();
    private static Scanner scanner = new Scanner(System.in);

    public MainOeuvreGUI(IMainOeuvreService mainOeuvre) {
        this.mainOeuvre = mainOeuvre;
    }

    public MainOeuvreGUI() {
    }

    public static void displayMenuMainOeuvre(Projet projet) {
        List<MainOeuvre> mainOeuvres = new ArrayList<>();

        System.out.println("--- Ajout de la main-d'œuvre ---");
        boolean addMo = true;

        while (addMo) {
            System.out.println("Entrez le nom de main-d'œuvre : ");
            String nom = scanner.nextLine();  // Read name of the labor

            System.out.println("Entrez le type de main-d'œuvre : \n 1. Ouvrier \n 2. Specialiste");
            int choice = scanner.nextInt();
            MainOeuvreType typeMo = null;

            switch (choice) {
                case 1:
                    typeMo = MainOeuvreType.Ouvrier;
                    break;
                case 2:
                    typeMo = MainOeuvreType.Specialiste;
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
                    // Clear the input buffer before the next attempt
                    scanner.nextLine();
                    continue;
            }

            // Now properly consume the newline character after numeric input
            scanner.nextLine();

            System.out.println("Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
            double tauxH = scanner.nextDouble();
            System.out.println("Entrez le nombre d'heures travaillées : ");
            double nbrH = scanner.nextDouble();
            System.out.println("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
            double facteur = scanner.nextDouble();

            // Clear the buffer again after numeric input
            scanner.nextLine();

            // Create MainOeuvre object and set values
            MainOeuvre mainO = new MainOeuvre();
            mainO.setNom(nom);
            mainO.setMainOeuvreType(typeMo);  // Ensure that typeMo is not null now
            mainO.setTauxHoraire(tauxH);
            mainO.setHeureTravail(nbrH);
            mainO.setProductiviteOuvrier(facteur);

            // Call the service to create MainOeuvre associated with the project
            mainOeuvre.createMainOeuvre(mainO, projet.getId());
            logger.info("Main-d'œuvre ajoutée avec succès !");

            // Ask if user wants to add another labor type
            System.out.println("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
            String choix = scanner.nextLine();
            if (choix.equalsIgnoreCase("n")) {
                addMo = false;
            }
        }

        // Add mainOeuvres to the project components
        for (MainOeuvre mainOeuvre : mainOeuvres) {
            projet.setComposants((List<Composant>) mainOeuvre);  // Add each mainOeuvre to the project
        }
    }

}
