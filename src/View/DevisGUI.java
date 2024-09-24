package View;

import Model.Devis;
import Model.Enum.EtatProjet;
import Model.Projet;
import Service.Implementation.DevisServiceImp;
import Service.Implementation.ProjetServiceImp;
import Service.Interface.IDevisService;
import Service.Interface.IProjetService;
import Utils.ValidationUtils;


import java.time.LocalDate;
import java.util.Scanner;

public class DevisGUI {
    private static IDevisService devisService = new DevisServiceImp();
    private static IProjetService projetService = new ProjetServiceImp();
    private static ProjetGUI projetGUI;

    public DevisGUI(IDevisService devisService, ProjetGUI projetGUI) {
        this.devisService = devisService;
        this.projetGUI = projetGUI;
    }

    public static void displayDevisMenu(Projet projet) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Enregistrement du Devis ---");
        System.out.println("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
        LocalDate dateEmission = ValidationUtils.readDate();
        System.out.println("Entrez la date de validité du devis (format : jj/mm/aaaa) : ");
        LocalDate dateValidite = ValidationUtils.readDate();

        double estimatedAmount = projet.getCoutTotal();
        System.out.println("Souhaitez-vous enregistrer le devis ? (y/n) : ");
        if (ValidationUtils.readYesNo()) {
            Devis devis = new Devis(estimatedAmount, dateEmission, dateValidite, true, projet.getId());
            devisService.createDevis(devis, projet.getId());
            projetService.updateStateProject(EtatProjet.Terminé, projet.getId());
            System.out.println("Devis enregistré avec succès !");
        } else {
            Devis devis = new Devis(estimatedAmount, dateEmission, dateValidite, false, projet.getId());
            devisService.createDevis(devis, projet.getId());
            projetService.updateStateProject(EtatProjet.Annulé, projet.getId());
            System.out.println("Enregistrement du devis annulé.");
        }
    }

}