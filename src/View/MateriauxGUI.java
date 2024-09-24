package View;

import Model.Composant;
import Model.Materiaux;
import Model.Projet;
import Service.Implementation.MateriauxServiceImp;
import Service.Interface.IMateriauxService;
import Utils.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.midi.VoiceStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MateriauxGUI {

    private static final Logger logger = LoggerFactory.getLogger(MateriauxGUI.class);
    private static IMateriauxService materialService = new MateriauxServiceImp();
    private static Scanner scanner = new Scanner(System.in);

    public MateriauxGUI(IMateriauxService materialService) {
        this.materialService = materialService;
    }

    public MateriauxGUI() {
    }

    public static void displayMenuMaterial(Projet projet) {
        Materiaux material = new Materiaux();
        List<Materiaux> materiaux = new ArrayList<>();
        System.out.println("--- Ajout des matériaux ---");
        boolean continueAdding = true;

        while (continueAdding) {
            System.out.println("Entrez le nom du matériau :");
            String nomMaterial = ValidationUtils.readString();
            System.out.println("Entrez la quantité de ce matériau (en m²) :");
            double quantite = ValidationUtils.readDouble();
            System.out.println("Entrez le coût unitaire de ce matériau (€/m²) :");
            double coutU = ValidationUtils.readDouble();
            System.out.println("Entrez le coût de transport de ce matériau (€) :");
            double coutT = ValidationUtils.readDouble();
            System.out.println("Entrez le coefficient de qualité du matériau :");
            double coeff = ValidationUtils.readDouble();
            scanner.nextLine();
            material.setNom(nomMaterial);
            material.setQuantite(quantite);
            material.setCoutUnitaire(coutU);
            material.setCoutTransport(coutT);
            material.setCoefficientQualite(coeff);

            materialService.createMateriaux(material,projet.getId());
            materiaux.add(material);

            System.out.println("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            String choix = scanner.nextLine();
            if (choix.equals("n")) {
                continueAdding = false;
                MainOeuvreGUI.displayMenuMainOeuvre(projet);
            }
        }
    }

}