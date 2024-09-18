import Repository.Implementation.ProjetImp;
import Model.Projet;
import Model.EtatProjet;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProjetImp projetImp = new ProjetImp();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Project");
            System.out.println("2. View All Projects");
            System.out.println("3. Find Project by ID");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Project Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Marg Beneficiaire: ");
                    double margeBeneficiaire = scanner.nextDouble();
                    System.out.print("Enter Cout Total: ");
                    double coutTotal = scanner.nextDouble();
                    System.out.print("Enter Etat Projet (e.g., ACTIVE, INACTIVE): ");
                    String stateInput = scanner.next();
                    EtatProjet etatProjet = EtatProjet.valueOf(stateInput);

                    Projet newProject = new Projet(id, name, margeBeneficiaire, coutTotal, etatProjet);
                    projetImp.addProject(newProject);
                    System.out.println("Project added successfully!");
                    break;

                case 2:
                    List<Projet> allProjects = projetImp.findAllProjects();
                    System.out.println("All Projects:");
                    for (Projet projet : allProjects) {
                        System.out.println(projet);
                    }
                    break;

                case 3:
                    System.out.print("Enter Project ID: ");
                    id = scanner.nextInt();
                    Projet projectById = projetImp.findProjectById(id);
                    if (projectById != null) {
                        System.out.println("Project Found: " + projectById);
                    } else {
                        System.out.println("Project not found.");
                    }
                    break;

                case 4:
                    running = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
