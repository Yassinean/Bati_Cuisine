package View;

import Model.*;
import Model.Enum.EtatProjet;
import Service.Implementation.ProjetServiceImp;
import Service.Interface.IProjetService;
import Utils.LoggerUtils;
import Utils.ValidationUtils;
import com.sun.tools.javac.Main;


import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProjetGUI {
    private IProjetService projetService = new ProjetServiceImp();

    private final Scanner scanner = new Scanner(System.in);

    public ProjetGUI(IProjetService projetService, MateriauxGUI materiauxGUI, MainOeuvreGUI mainOeuvreGUI) {
        this.projetService = projetService;
    }

    public ProjetGUI() {
    }

    public void creerNouveauProjet(Client client) {
        Projet projet = new Projet();

        System.out.println("--- Création d'un Nouveau Projet ---");
        System.out.println("Entrez le nom du projet : ");
        String nomProjet = ValidationUtils.readString();
        System.out.println("Entrez la surface (en m²) : ");
        double surface = ValidationUtils.readDouble();

        projet.setNomProjet(nomProjet);
        projet.setSurface(surface);
        projet.setEtatProjet(EtatProjet.Encours);
        projet.setClient(client);

        // Save project and retrieve the updated project with the generated ID
        projetService.createProject(projet, client.getId());
        System.out.println("===================================");
        System.out.println("Projet : " + projet.getNomProjet() + " créé avec succès");
        System.out.println("===================================");

        MateriauxGUI.displayMenuMaterial(projet);

        System.out.println("--- Calcul du coût total ---");
        System.out.println("Calcul en cours ...");

        displayProjectCostDetails(projet);

    }


    public void displayProjectCostDetails(Projet project) {
        Projet projet = projetService.getProjectById(project.getId());
        if (projet != null) {
            System.out.println("==================================");
            System.out.println("         Détails du Projet        ");
            System.out.println("==================================");
            System.out.println("| Nom du projet : " + projet.getNomProjet() + " | ");
            if (projet.getClient() != null) {
                System.out.println("Client : " + projet.getClient().getNom());
                System.out.println("Adresse du chantier : " + projet.getClient().getAddress());
            } else {
                System.out.println("Client : Non défini");
            }
            System.out.println("Surface : " + projet.getSurface() + " m²\n");

            System.out.println("--- Détail des Matériaux ---");
            double coutTotalMateriaux = 0;
            for (Materiaux materiel : projet.getMateriauxes()) {
                double coutMateriel = (materiel.getQuantite() * materiel.getCoutUnitaire() * materiel.getCoefficientQualite()) + materiel.getCoutTransport();
                coutTotalMateriaux += coutMateriel;
                System.out.printf("- %s : %.2f € (quantité : %.2f, coût unitaire : %.2f €/unité, qualité : %.2f, transport : %.2f €)\n",
                        materiel.getNom(), coutMateriel, materiel.getQuantite(), materiel.getCoutUnitaire(), materiel.getCoefficientQualite(), materiel.getCoutTransport());
            }
            System.out.printf("**Coût total des matériaux avant TVA : %.2f €**\n", coutTotalMateriaux);
            System.out.printf("**Coût total des matériaux avec TVA (20%%) : %.2f €**\n", coutTotalMateriaux * 1.2);

            System.out.println("\n--- Détail de la Main-d'œuvre ---");
            double coutTotalMainOeuvre = 0;
            for (MainOeuvre mainDoeuvre : projet.getMainOeuvres()) {
                double coutMainOeuvre = mainDoeuvre.getHeureTravail() * mainDoeuvre.getTauxHoraire() * mainDoeuvre.getProductiviteOuvrier();
                coutTotalMainOeuvre += coutMainOeuvre;
                System.out.printf("- %s : %.2f € (taux horaire : %.2f €/h, heures travaillées : %.2f h, productivité : %.2f)\n",
                        mainDoeuvre.getNom(), coutMainOeuvre, mainDoeuvre.getTauxHoraire(), mainDoeuvre.getHeureTravail(), mainDoeuvre.getProductiviteOuvrier());
            }
            System.out.printf("**Coût total de la main-d'œuvre avant TVA : %.2f €**\n", coutTotalMainOeuvre);
            System.out.printf("**Coût total de la main-d'œuvre avec TVA (20%%) : %.2f €**\n", coutTotalMainOeuvre * 1.2);

            double coutTotalAvantMarge = coutTotalMateriaux + coutTotalMainOeuvre;
            System.out.printf("\n--- Coût total avant marge : %.2f € ---\n", coutTotalAvantMarge);

            LoggerUtils.logInfo("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (y/n) : ");
            String appliqueMarge = scanner.next();
            double margeBeneficiaire = 0.0;
            if (appliqueMarge.equalsIgnoreCase("y")) {
                LoggerUtils.logInfo("Entrez le pourcentage de marge bénéficiaire (%) : ");
                double pourcentageMarge = scanner.nextDouble();
                margeBeneficiaire = coutTotalAvantMarge * (pourcentageMarge / 100);
            }

            double coutTotalFinal = coutTotalAvantMarge + margeBeneficiaire;
            System.out.printf("Marge bénéficiaire (%.2f%%) : %.2f €\n", (margeBeneficiaire / coutTotalAvantMarge) * 100, margeBeneficiaire);
            System.out.printf("**Coût total final du projet : %.2f €**\n", coutTotalFinal);
            projet.setCoutTotal(coutTotalFinal);
            projet.setMargeBeneficiaire(margeBeneficiaire);
            projetService.updateProject(project.getId(), projet);
        } else {
            LoggerUtils.logError("Projet non trouvé.");
        }

        DevisGUI.displayDevisMenu(projet);
    }


    public void displayAllProjects() {
        Map<Integer, Projet> projects = projetService.getAllProjects();

        if (projects.isEmpty()) {
            System.out.println("Aucun projet trouvé.");
        } else {
            for (Projet project : projects.values()) {
                displayProjectDetails(project);
            }
        }
    }

    private void displayProjectDetails(Projet project) {
        System.out.println("==================================");
        System.out.println("         Détails du Projet       ");
        System.out.println("==================================");
        System.out.println("|| Nom du projet : " + project.getNomProjet());
        System.out.println("|| Statut du projet : " + project.getEtatProjet());
        System.out.println("|| Marge de profit : " + project.getMargeBeneficiaire() + " €");

        Client client = project.getClient();
        if (client != null) {
            System.out.println("|| Client : " + client.getNom());
            System.out.println("|| Adresse du client : " + client.getAddress());
            System.out.println("|| Numéro de téléphone : " + client.getTelephone());
            System.out.println("|| Professionnel : " + (client.isEstProfessionel() ? "Oui" : "Non"));
        } else {
            System.out.println("Client : Inconnu");
        }

        List<Materiaux> materiauxes = project.getMateriauxes();
        List<MainOeuvre> mainOeuvres = project.getMainOeuvres();

        System.out.println("\n====== Composants du Projet ======");
        if ((materiauxes != null && !materiauxes.isEmpty()) || (mainOeuvres != null && !mainOeuvres.isEmpty())) {
            if (materiauxes != null && !materiauxes.isEmpty()) {
                System.out.println("--------- Matériaux : ---------");
                for (Materiaux materiaux : materiauxes) {
                    System.out.printf("  - %s : Coût Unitaire : %.2f €, Quantité : %.2f, Coût de Transport : %.2f €, Coefficient de Qualité : %.2f\n",
                            materiaux.getNom(), materiaux.getCoutUnitaire(), materiaux.getQuantite(), materiaux.getCoutTransport(), materiaux.getCoefficientQualite());
                }
            } else {
                System.out.println("Aucun matériau disponible.");
            }

            if (mainOeuvres != null && !mainOeuvres.isEmpty()) {
                System.out.println("--------- Main d'œuvre : ---------");
                for (MainOeuvre mainOeuvre : mainOeuvres) {
                    System.out.printf("  - %s : Taux Horaire : %.2f €/h, Heures Travaillées : %.2f, Productivité : %.2f\n",
                            mainOeuvre.getNom(), mainOeuvre.getTauxHoraire(), mainOeuvre.getHeureTravail(), mainOeuvre.getProductiviteOuvrier());
                }
            } else {
                System.out.println("Aucune main d'œuvre disponible.");
            }
        } else {
            System.out.println("Aucun composant pour ce projet.");
        }

        System.out.printf("\nCoût total estimé : %.2f €\n", project.getCoutTotal());
        System.out.println("===============================");
    }


    public void projectTotalCost() {
        System.out.println("Veuillez entrer l'ID du projet : ");
        int id = ValidationUtils.readInt();
        Projet project = projetService.getProjectById(id);
        if (project == null) {
            System.out.println("Projet n'existe pas");
        } else {
            displayProjectCostDetails(project);
        }
    }


}
