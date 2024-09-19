package View;

import Service.Interface.IMateriauxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class MateriauxGUI {

    private static final Logger logger = LoggerFactory.getLogger(MateriauxGUI.class);
    private IMateriauxService materialService;
    private Scanner scanner = new Scanner(System.in);

    public MateriauxGUI(IMateriauxService materialService) {
        this.materialService = materialService;
    }

    public void displayMenuMaterial() {
        System.out.println("--- Ajout des matériaux ---");
        System.out.println("Entrez le nom du matériau :");
        String nomMaterial = scanner.nextLine();
        System.out.println("Entrez la quantité de ce matériau (en m²) :");
        double quantite = scanner.nextDouble();
        System.out.println("Entrez le coût unitaire de ce matériau (€/m²) :");
        double coutU = scanner.nextDouble();
        System.out.println("Entrez le coût de transport de ce matériau (€) :");
        double coutT = scanner.nextDouble();
        System.out.println("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) :");
        double coeff = scanner.nextDouble();
    }
}