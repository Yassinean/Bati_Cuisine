package View;

import Model.Devis;
import Model.Enum.EtatProjet;
import Model.Projet;
import Service.Implementation.DevisServiceImp;
import Service.Interface.IDevisService;
import Utils.ValidationUtils;


import java.time.LocalDate;
import java.util.Scanner;

public class DevisGUI {
    private static IDevisService devisService = new DevisServiceImp();
    private static ProjetGUI projetGUI;

    public DevisGUI(IDevisService devisService, ProjetGUI projetGUI) {
        this.devisService = devisService;
        this.projetGUI = projetGUI;
    }

    public static void displayDevisMenu(Projet projet) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Enregistrement du Devis ---");
        LocalDate dateEmission = ValidationUtils.validateDateInput("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
        LocalDate dateValidite = ValidationUtils.validateDateInput("Entrez la date de validité du devis (format : jj/mm/aaaa) : ");

        double estimatedAmount = projet.getCoutTotal();

        if (ValidationUtils.validateYesNoInput("Souhaitez-vous enregistrer le devis ? (y/n) : ")) {
            Devis devis = new Devis(estimatedAmount, dateEmission, dateValidite, true, projet.getId());
            devisService.createDevis(devis, projet.getId());
            projet.setEtatProjet(EtatProjet.valueOf("Terminé"));
            System.out.println("Devis enregistré avec succès !");
        } else {
            projet.setEtatProjet(EtatProjet.valueOf("Annulé"));
            System.out.println("Enregistrement du devis annulé.");
        }
    }

}