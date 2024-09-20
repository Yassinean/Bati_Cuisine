package View;

import Service.Interface.IProjetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ProjetGUI {
    private static final Logger logger = LoggerFactory.getLogger(ProjetGUI.class);
    private IProjetService projetService;
    private MateriauxGUI materiauxGUI;
    private MainOeuvreGUI mainOeuvreGUI;

    private Scanner scanner = new Scanner(System.in);

    public ProjetGUI(IProjetService projetService,MateriauxGUI materiauxGUI, MainOeuvreGUI mainOeuvreGUI) {
        this.projetService = projetService;
        this.materiauxGUI = materiauxGUI;
        this.mainOeuvreGUI = mainOeuvreGUI;
    }

    public ProjetGUI(){}

    public void displayMenuProject() {
        System.out.println("--- Création d'un Nouveau Projet ---");
        System.out.print("Entrez le nom du projet : ");
        String nomProjet = scanner.nextLine();
        System.out.print("Entrez la surface de la cuisine (en m²) : ");
        double surface = scanner.nextDouble();
        scanner.nextLine();


        materiauxGUI.displayMenuMaterial();
        mainOeuvreGUI.displayMenuMainOeuvre();
    }
}
