package View;

import Model.Client;
import Model.Projet;
import Service.Interface.IClientService;
import Utils.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ClientGUI {

    private static final Logger logger = LoggerFactory.getLogger(ClientGUI.class);
    private static IClientService clientService;
    private static ProjetGUI projetGUI;


    public ClientGUI(IClientService clientService, ProjetGUI projetGUI) {
        ClientGUI.clientService = clientService;
        ClientGUI.projetGUI = projetGUI;
        Scanner scanner = new Scanner(System.in);
    }

    public ClientGUI() {
    }

    public static void displayMenuClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Recherche de client ---");
        System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
        System.out.println("1. Chercher un client existant");
        System.out.println("2. Ajouter un nouveau client");
        System.out.print("Choisissez une option : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1:
                rechercherClient(scanner);
                break;
            case 2:
                ajouterNouveauClient(scanner);
                break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
                System.out.println(clientService);
                displayMenuClient();
                break;
        }
    }

    private static void rechercherClient(Scanner scanner) {
        System.out.println("--- Recherche de client existant ---");
        System.out.println("Entrez le nom du client : ");
        String nom = ValidationUtils.readString();

        Client client = clientService.getClientByName(nom);
        if (client != null) {
            System.out.println("Client trouvé !");
            System.out.println(client);
            System.out.print("Souhaitez-vous créer un projet pour ce client ? (y/n) : ");
            String continuer = scanner.nextLine();
            if (continuer.equalsIgnoreCase("y")) {
                projetGUI.creerNouveauProjet(client);
            }
        } else {
            System.out.println("Client non trouvé.");
        }
    }


    private static void ajouterNouveauClient(Scanner scanner) {
        Projet projet = new Projet();
        System.out.println("--- Ajouter un nouveau client ---");
        System.out.println("Entrez le nom du client : ");
        String nom = ValidationUtils.readString();
        System.out.println("Entrez l'adresse du client : ");
        String adresse = ValidationUtils.readString();
        System.out.println("Entrez le numéro de téléphone du client : ");
        String telephone = ValidationUtils.readPhoneNumber();
        System.out.println("Le client est-il un professionnel ? (y/n) : ");
        boolean isProfessional = ValidationUtils.readYesNo();

        Client client = new Client(nom, adresse, telephone, isProfessional);
        clientService.createClient(client);

        System.out.println("===========================");
        System.out.println("|  Client ajouté avec succès ! |");
        System.out.println("===========================");
        System.out.println("Souhaitez-vous continuer avec ce client ? (y/n) : ");
        boolean continuer = ValidationUtils.readYesNo();

        if (continuer) {
            projetGUI.creerNouveauProjet(client);
        }else return;
    }
}
