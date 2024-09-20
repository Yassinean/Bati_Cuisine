package View;

import Model.Client;
import Service.Interface.IClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Scanner;

public class ClientGUI {

    private static final Logger logger = LoggerFactory.getLogger(ClientGUI.class);
    private IClientService clientService;
    private Scanner scanner = new Scanner(System.in);
    private ProjetGUI projetGUI;

    public ClientGUI(IClientService clientService, ProjetGUI projetGUI) {
        this.clientService = clientService;
        this.projetGUI = projetGUI;
    }

    public ClientGUI(){}

    public void displayClientMenu() {
        System.out.println("--- Recherche de client ---");
        System.out.println("1. Chercher un client existant");
        System.out.println("2. Ajouter un nouveau client");
        System.out.print("Choisissez une option : ");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                chercherClient();
                break;
            case 2:
                ajouterClient();
                break;
            default:
                System.out.println("Choix invalide.");
                break;
        }
    }

    public void ajouterClient() {
        System.out.println("== Ajouter un nouveau client ==");

        System.out.print("Nom du client: ");
        String nom = scanner.nextLine();

        System.out.print("Adresse: ");
        String adresse = scanner.nextLine();

        System.out.print("Téléphone: ");
        String telephone = scanner.nextLine();

        System.out.print("Est-ce le client est professionnel? (oui/non): ");
        String reponse = scanner.nextLine();
        boolean estProfessionnel = reponse.equalsIgnoreCase("oui");

        Client client = new Client();
        client.setNom(nom);
        client.setAddress(adresse);
        client.setTelephone(telephone);
        client.setEstProfessionel(estProfessionnel);

        clientService.createClient(client);
        logger.info("Client ajouté avec succès.");
    }

    public void chercherClient() {
        System.out.println("--- Recherche de client existant ---");
        System.out.print("Entrez le nom du client : ");
        String name = scanner.nextLine();

        Client client = clientService.getClientByName(name);
        if (client != null) {
            System.out.println("Client trouvé !");
            System.out.println("Nom: " + client.getNom());
            System.out.println("Adresse: " + client.getAddress());
            System.out.println("Téléphone: " + client.getTelephone());
            System.out.println("Professionnel: " + client.isEstProfessionel());

            System.out.print("Souhaitez-vous continuer avec ce client ? (y/n) : ");
            char choice = scanner.nextLine().charAt(0);
            if (choice == 'y') {
                System.out.println("--- Création d'un Nouveau Projet ---");
                System.out.print("Entrez le nom du projet : ");
                projetGUI.displayMenuProject();
            } else {
                System.out.println("Retour au menu principal.");
            }
        } else {
            System.out.println("Aucun client trouvé avec le nom " + name);
        }
    }
}
