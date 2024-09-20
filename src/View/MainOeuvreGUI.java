package View;

import Service.Interface.IMainOeuvreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class MainOeuvreGUI {
    private static final Logger logger = LoggerFactory.getLogger(MainOeuvreGUI.class);
    private IMainOeuvreService mainOeuvre;
    private Scanner scanner = new Scanner(System.in);

    public MainOeuvreGUI(IMainOeuvreService mainOeuvre) {
        this.mainOeuvre = mainOeuvre;
    }

    public MainOeuvreGUI(){}

    public void displayMenuMainOeuvre() {
        System.out.println("--- Ajout de la main-d'œuvre ---");
        System.out.println("Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Spécialiste) : ");
        String typeMo = scanner.nextLine();
        System.out.println("Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
        double tauxH = scanner.nextDouble();
        System.out.println("Entrez le nombre d'heures travaillées : ");
        double nbrH = scanner.nextDouble();
        System.out.println("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
        double facteur = scanner.nextDouble();
        logger.info("Main-d'œuvre ajoutée avec succès !");
        System.out.println("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
    }
}
