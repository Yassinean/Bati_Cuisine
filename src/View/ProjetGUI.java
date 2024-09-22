package View;

import Model.*;
import Model.Enum.EtatProjet;
import Service.Implementation.DevisServiceImp;
import Service.Implementation.ProjetServiceImp;
import Service.Interface.IProjetService;
import Utils.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ProjetGUI {
    private static final Logger logger = LoggerFactory.getLogger(ProjetGUI.class);
    private IProjetService projetService = new ProjetServiceImp();
    private MateriauxGUI materiauxGUI;
    private MainOeuvreGUI mainOeuvreGUI;

    private Scanner scanner = new Scanner(System.in);

    public ProjetGUI(IProjetService projetService, MateriauxGUI materiauxGUI, MainOeuvreGUI mainOeuvreGUI) {
        this.projetService = projetService;
        this.materiauxGUI = materiauxGUI;
        this.mainOeuvreGUI = mainOeuvreGUI;
    }

    public ProjetGUI() {
    }

    public void creerNouveauProjet(Client client) {
        Projet projet = new Projet();

        System.out.println("--- Création d'un Nouveau Projet ---");
        System.out.print("Entrez le nom du projet : ");
        String nomProjet = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Entrez la surface (en m²) : ");
        double surface = scanner.nextDouble();
        scanner.nextLine();

        projet.setNomProjet(nomProjet);
        projet.setSurface(surface);
        projet.setEtatProjet(EtatProjet.Encours);
        projet.setClient(client);

        // Save project and retrieve the updated project with the generated ID
        projetService.createProject(projet, client.getId());

        System.out.println("Projet : " + projet.getNomProjet() + "créé avec succès");

        // Pass the project with its correct ID to the material and labor GUIs
        MateriauxGUI.displayMenuMaterial(projet);

        System.out.println("--- Calcul du coût total ---");
        System.out.println("Souhaitez-vous appliquer une TVA au projet ? (y/n) : ");
        String tva = scanner.nextLine();
        double tauxTva = 0;
        if (tva.equalsIgnoreCase("y")) {
            System.out.println("Entrez le pourcentage de TVA (%) : ");
            tauxTva = scanner.nextDouble();
            scanner.nextLine();
        }
        System.out.println("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (y/n) : ");
        String marge = scanner.nextLine();
        double profit = 0;
        if (marge.equalsIgnoreCase("y")) {
            System.out.println("Entrez le pourcentage de marge bénéficiaire (%) : ");
            profit = scanner.nextDouble();
            scanner.nextLine();
        }

        displayProjectCostDetails(projet);

    }


//    public void calculerCoutTotalProjet(Projet project) {
//        Scanner scanner = new Scanner(System.in);
//
////        projetService.applyTaxAndProfitMargin(project, taxRate, profitMargin);
//        System.out.println("TVA et marge bénéficiaire appliquées avec succès.");
//        displayProjectCostDetails(project);
//        saveQuote(project);
//
//    }

    public void displayProjectCostDetails(Projet project) {
        System.out.println("--- Résultat du Calcul ---");

        Projet projet = projetService.getProjectById(project.getId());
        if (projet != null) {
            System.out.println("--- Détails du Projet ---");
            System.out.println("Nom du projet : " + projet.getNomProjet());
            if (projet.getClient() != null) {
                System.out.println("Client : " + projet.getClient().getNom());
                System.out.println("Adresse du chantier : " + projet.getClient().getAddress());
            } else {
                System.out.println("Client : Non défini");
            }
            System.out.println("Surface : " + projet.getSurface() + " m²");

            System.out.println("\n--- Détail des Matériaux ---");
            double coutTotalMateriaux = 0;
            for (Materiaux materiel : projet.getMateriauxes()) {
                double coutMateriel = materiel.getQuantite() * materiel.getCoutUnitaire() + materiel.getCoutTransport();
                coutTotalMateriaux += coutMateriel;
                System.out.printf("- %s : %.2f € (quantité : %.2f, coût unitaire : %.2f €/unité, qualité : %.2f, transport : %.2f €)\n",
                        materiel.getNom(), coutMateriel, materiel.getQuantite(), materiel.getCoutUnitaire(), materiel.getCoefficientQualite(), materiel.getCoutTransport());
            }
            System.out.printf("**Coût total des matériaux avant TVA : %.2f €**\n", coutTotalMateriaux);
            System.out.printf("**Coût total des matériaux avec TVA (20%%) : %.2f €**\n", coutTotalMateriaux * 1.2);

            System.out.println("\n--- Détail de la Main-d'œuvre ---");
            double coutTotalMainOeuvre = 0;
            for (MainOeuvre mainDoeuvre : projet.getMainOeuvres()) {
                double coutMainOeuvre = mainDoeuvre.getHeureTravail() * mainDoeuvre.getTauxHoraire();
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
        } else {
            LoggerUtils.logError("Projet non trouvé.");
        }
    }


    private void saveQuote(Projet project) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Enregistrement du Devis ---");
        System.out.println("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
        String issueDateStr = scanner.nextLine();
        LocalDate issueDate = parseDate(issueDateStr);

        System.out.println("Entrez la date de validité du devis (format : jj/mm/aaaa) : ");
        String validityDateStr = scanner.nextLine();
        LocalDate validityDate = parseDate(validityDateStr);

        double estimatedAmount = project.getCoutTotal();

//        System.out.print("Souhaitez-vous enregistrer le devis ? (y/n) : ");
//        String confirmSave = scanner.nextLine();
//
//        if (confirmSave.equalsIgnoreCase("y")) {
//            Devis devis = new Devis();
//            devis.setProject(project);
//            DevisServiceImp. (devis);
//
//            System.out.println("Devis enregistré avec succès !");
//        } else {
//            System.out.println("Enregistrement du devis annulé.");
//        }
    }

    private LocalDate parseDate(String dateStr) {
        return LocalDate.parse(dateStr, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void displayAllProjects() {
        List<Projet> projects = projetService.getAllProjects();

        if (projects.isEmpty()) {
            System.out.println("Aucun projet trouvé.");
        } else {
            for (Projet project : projects) {
                displayProjectDetails(project);
            }
        }
    }

    private void displayProjectDetails(Projet project) {
        System.out.println("--- Détails du Projet ---");
        System.out.println("Nom du projet : " + project.getNomProjet());
        System.out.println("Statut du projet : " + project.getEtatProjet());
        System.out.println("Marge de profit : " + project.getMargeBeneficiaire());

        Client client = project.getClient();
        if (client != null) {
            System.out.println("Client : " + client.getNom());
            System.out.println("Adresse du client : " + client.getAddress());
            System.out.println("Numéro de téléphone : " + client.getTelephone());
            System.out.println("Professionnel : " + (client.isEstProfessionel() ? "Oui" : "Non"));
        } else {
            System.out.println("Client : Inconnu");
        }

        List<Composant> composants = project.getComposants();
        if (composants != null && !composants.isEmpty()) {
            System.out.println(" -- Composants du projet : -- ");
            System.out.println(" - Matériaux : ");
            for (Composant composant : composants) {
                if (composant instanceof Materiaux) {
                    System.out.println("   Type : Material ");
                    System.out.println("   Nom : " + composant.getNom());
                    System.out.println("   Coût Unitaire : " + ((Materiaux) composant).getCoutUnitaire());
                    System.out.println("   Quantité : " + ((Materiaux) composant).getQuantite());

                    System.out.println("   Coût de transport : " + ((Materiaux) composant).getCoutTransport());
                    System.out.println("   Coefficient de qualité : " + ((Materiaux) composant).getCoefficientQualite());
                }
            }
            System.out.println(" - Main d'oeuvre : ");
            for (Composant component : composants) {
                if (component instanceof MainOeuvre) {
                    System.out.println("   Type : Labor ");
                    System.out.println("   Nom : " + component.getNom());
                    System.out.println("   Taux horaire : " + ((MainOeuvre) component).getTauxHoraire());
                    System.out.println("   Heures travaillées : " + ((MainOeuvre) component).getHeureTravail());

                    System.out.println("   Productivité du travailleur : " + ((MainOeuvre) component).getProductiviteOuvrier());
                }
            }
        } else {
            System.out.println("Aucun composant pour ce projet.");
        }

        System.out.println("Coût total estimé : " + project.getCoutTotal() + " €");
        System.out.println("-------------------------------");
    }

    public void projectTotalCost(int id) {
        Projet project = projetService.getProjectById(id);
        if (project == null) {
            System.out.println("Projet n'existe pas");
        } else {
            displayProjectCostDetails(project);
        }
    }


}
