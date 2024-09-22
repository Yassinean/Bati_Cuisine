package View;

import Model.*;
import Model.Enum.EtatProjet;
import Service.Implementation.DevisServiceImp;
import Service.Implementation.ProjetServiceImp;
import Service.Interface.IProjetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
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
        System.out.println("Entrez la surface (en m²) : ");
        double surface = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        projet.setNomProjet(nomProjet);
        projet.setSurface(surface);
        projet.setEtatProjet(EtatProjet.Encours);
        projet.setClient(client);  // Associate the project with the client

        // Save project and retrieve the updated project with the generated ID
        projetService.createProject(projet);

        System.out.println("Projet créé avec succès, ID : " + projet.getId());

        // Pass the project with its correct ID to the material and labor GUIs
        MateriauxGUI.displayMenuMaterial(projet);
    }


//    public void calculerCoutTotalProjet(Projet project) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("--- Calcul du coût total ---");
//        System.out.println("Souhaitez-vous appliquer une TVA au projet ? (y/n) : ");
//        String applyTax = scanner.nextLine();
//        double taxRate = 0;
//        if (applyTax.equalsIgnoreCase("y")) {
//            System.out.println("Entrez le pourcentage de TVA (%) : ");
//            taxRate = scanner.nextDouble();
//            scanner.nextLine();
//        }
//
//        System.out.println("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (y/n) : ");
//        String applyMargin = scanner.nextLine();
//        double profitMargin = 0;
//        if (applyMargin.equalsIgnoreCase("y")) {
//            System.out.println("Entrez le pourcentage de marge bénéficiaire (%) : ");
//            profitMargin = scanner.nextDouble();
//            scanner.nextLine();
//        }
//
////        projetService.applyTaxAndProfitMargin(project, taxRate, profitMargin);
//        System.out.println("TVA et marge bénéficiaire appliquées avec succès.");
//        displayProjectCostDetails(project);
//        saveQuote(project);
//
//    }
//
//    public void displayProjectCostDetails(Projet project) {
//        System.out.println("--- Résultat du Calcul ---");
//
//        System.out.println("Nom du projet : " + project.getNomProjet());
//        Client client = project.getClient();
//        System.out.println("Client : " + client.getNom());
//        System.out.println("Adresse du client : " + client.getAddress());
//
//        System.out.println("--- Détail des Coûts ---");
//
//        System.out.println("1. Matériaux :");
//        double totalMaterialCostBeforeTax = 0;
//        double totalMaterialCostAfterTax = 0;
//        double materialTaxRate = 0;
//        for (Composant component : project.getComposants()) {
//            if (component instanceof Materiaux) {
//                Materiaux material = (Materiaux) component;
//                double coutAvantTax = material.getCoutUnitaire() * material.getQuantite() * material.getCoefficientQualite() + material.getCoutTransport();
//                double costApresTax = coutAvantTax + (coutAvantTax * (material.getTauxTva() / 100));
//                totalMaterialCostBeforeTax += coutAvantTax;
//                totalMaterialCostAfterTax += costApresTax;
//                materialTaxRate = material.getTauxTva();
//                System.out.printf("- %s : %.2f € (avant TVA : %.2f €, quantité : %.2f, coût unitaire : %.2f €/unité, transport : %.2f €)%n",
//                        material.getNom(), costApresTax, coutAvantTax, material.getQuantite(), material.getCoutUnitaire(), material.getCoutTransport());
//            }
//        }
//        System.out.printf("Coût total des matériaux avant TVA : %.2f €%n", totalMaterialCostBeforeTax);
//        System.out.printf("Coût total des matériaux avec TVA (%.0f%%) : %.2f €%n", materialTaxRate, totalMaterialCostAfterTax);
//
//        System.out.println("2. Main-d'œuvre :");
//        double totalLaborCostBeforeTax = 0;
//        double totalLaborCostAfterTax = 0;
//        double mainOeuvreTaxRate = 0;
//        for (Composant component : project.getComposants()) {
//            if (component instanceof MainOeuvre) {
//                MainOeuvre mainOeuvre = (MainOeuvre) component;
//                double coutAvantTax = mainOeuvre.getTauxHoraire() * mainOeuvre.getHeureTravail() * mainOeuvre.getProductiviteOuvrier();
//                double coutApresTax = coutAvantTax + (coutAvantTax * (mainOeuvre.getTauxTva() / 100));
//                totalLaborCostBeforeTax += coutAvantTax;
//                totalLaborCostAfterTax += coutApresTax;
//                mainOeuvreTaxRate = mainOeuvre.getTauxTva();
//                System.out.printf("- %s : %.2f € (avant TVA : %.2f €, taux horaire : %.2f €/h, heures travaillées : %.2f h)%n",
//                        mainOeuvre.getNom(), coutApresTax, coutAvantTax, mainOeuvre.getTauxHoraire(), mainOeuvre.getHeureTravail());
//            }
//        }
//        System.out.printf("Coût total de la main-d'œuvre avant TVA : %.2f €%n", totalLaborCostBeforeTax);
//        System.out.printf("Coût total de la main-d'œuvre avec TVA (%.0f%%) : %.2f €%n", mainOeuvreTaxRate, totalLaborCostAfterTax);
//
//        double totalCostBeforeMargin = totalMaterialCostAfterTax + totalLaborCostAfterTax;
//        System.out.printf("Coût total avant marge : %.2f €%n", totalCostBeforeMargin);
//
////        if (project.getProfitMargin() > 0) {
////            double marginAmount = totalCostBeforeMargin * (project.getProfitMargin() / 100);
////            double totalCostAfterMargin = totalCostBeforeMargin + marginAmount;
////            System.out.printf("Marge bénéficiaire (%.2f%%) : %.2f €%n", project.getProfitMargin(), marginAmount);
////            System.out.printf("Coût total final du projet : %.2f €%n", totalCostAfterMargin);
////        } else {
////            System.out.printf("Coût total final du projet : %.2f €%n", totalCostBeforeMargin);
////        }
//    }
//
//    private void saveQuote(Projet project) {
//
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("--- Enregistrement du Devis ---");
//        System.out.println("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
//        String issueDateStr = scanner.nextLine();
//        LocalDate issueDate = parseDate(issueDateStr);
//
//        System.out.println("Entrez la date de validité du devis (format : jj/mm/aaaa) : ");
//        String validityDateStr = scanner.nextLine();
//        LocalDate validityDate = parseDate(validityDateStr);
//
//        double estimatedAmount = project.getCoutTotal();
//
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
//    }
//
//    private LocalDate parseDate(String dateStr) {
//        return LocalDate.parse(dateStr, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//    }
//
//    public void displayAllProjects() {
//        List<Project> projects = projetService.getAllProjects();
//
//        if (projects.isEmpty()) {
//            System.out.println("Aucun projet trouvé.");
//        } else {
//            for (Projet project : projects) {
//                displayProjectDetails(project);
//            }
//        }
//    }
//
//    private void displayProjectDetails(Projet project) {
//        System.out.println("--- Détails du Projet ---");
//        System.out.println("Nom du projet : " + project.getProjectName());
//        System.out.println("Statut du projet : " + project.getStatus());
//        System.out.println("Marge de profit : " + project.getProfitMargin());
//
//        Client client = project.getClient();
//        if (client != null) {
//            System.out.println("Client : " + client.getName());
//            System.out.println("Adresse du client : " + client.getAddress());
//            System.out.println("Numéro de téléphone : " + client.getPhoneNumber());
//            System.out.println("Professionnel : " + (client.getProfessional() ? "Oui" : "Non"));
//        } else {
//            System.out.println("Client : Inconnu");
//        }
//
//        List<Component> components = project.getComponents();
//        if (components != null && !components.isEmpty()) {
//            System.out.println(" -- Composants du projet : -- ");
//            System.out.println(" - Matériaux : ");
//            for (Component component : components) {
//                if (component instanceof Material) {
//                    System.out.println("   Type : Material ");
//                    System.out.println("   Nom : " + component.getName());
//                    System.out.println("   Coût Unitaire : " + ((Material) component).getUnitCost());
//                    System.out.println("   Quantité : " + ((Material) component).getQuantity());
//
//                    System.out.println("   Coût de transport : " + ((Material) component).getTransportCost());
//                    System.out.println("   Coefficient de qualité : " + ((Material) component).getQualityCoefficient());
//                }
//            }
//            System.out.println(" - Main d'oeuvre : ");
//            for (Component component : components) {
//                if (component instanceof Labor) {
//                    System.out.println("   Type : Labor ");
//                    System.out.println("   Nom : " + component.getName());
//                    System.out.println("   Taux horaire : " + ((Labor) component).getHourlyRate());
//                    System.out.println("   Heures travaillées : " + ((Labor) component).getWorkHours());
//
//                    System.out.println("   Productivité du travailleur : " + ((Labor) component).getWorkerProductivity());
//                }
//            }
//        } else {
//            System.out.println("Aucun composant pour ce projet.");
//        }
//
//        System.out.println("Coût total estimé : " + project.getTotalCost() + " €");
//        System.out.println("-------------------------------");
//    }
//
//    public void projectTotalCost(int id) {
//        Projet project = projetService.getProjectById(id);
//        if (project == null) {
//            System.out.println("Projet n'existe pas");
//        } else {
//            displayProjectCostDetails(project);
//        }
//    }
//

}
