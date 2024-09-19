package View;

import Model.Client;
import Service.Interface.IClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClientGUI {

    private static final Logger logger = LoggerFactory.getLogger(ClientGUI.class);
    private IClientService clientService;
    private Scanner scanner = new Scanner(System.in);

    public ClientGUI(IClientService clientService) {
        this.clientService = clientService;
    }

    public void displayMenu() {

        int choice;
        System.out.println("--- Recherche de client ---");
        System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
        System.out.println("1. Chercher un client existant");
        System.out.println("2. Ajouter un nouveau client");
        System.out.println("Choisissez une option :");
        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                chercherClient();
                break;
            case 2:
                ajouterClient();
                break;
            case 0:
                System.out.println("Exiting client menu...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }


    // worked
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


        logger.info("Client added successfully.");

    }

//    public void updateClient() {
//        System.out.println("== Mettre à jour un client ==");
//
//        System.out.print("Entrez l'ID du client à mettre à jour: ");
//        int clientId = scanner.nextInt();
//
//        System.out.println(clientService.getClientById(clientId));
//        if (existingClientOpt.isPresent()) {
//            Client existingClient = existingClientOpt.get();
//            System.out.println("Client trouvé: " + existingClient.getNom());
//
//            System.out.print("Nouveau nom: ");
//            String newName = scanner.nextLine();
//            existingClient.setNom(newName);
//
//            System.out.print("Nouvelle adresse: ");
//            String newAddress = scanner.nextLine();
//            existingClient.setAddress(newAddress);
//
//            System.out.print("Nouveau téléphone: ");
//            String newPhone = scanner.nextLine();
//            existingClient.setTelephone(newPhone);
//
//            clientService.updateClient(clientId,existingClient);
//
//            System.out.println("Client updated successfully.");
//        }
//    }

//    public void deleteClient() {
//        System.out.println("== Supprimer un client ==");
//
//        System.out.print("ID du client à supprimer: ");
//        String clientId = scanner.nextLine();
//
//        Optional<Client> existingClientOpt = Optional.ofNullable(clientService.getClientById(Integer.valueOf(clientId)));
//        if (existingClientOpt.isPresent()) {
//            clientService.deleteClient(UUID.fromString(clientId));
//            System.out.println("Client supprimé avec succès.");
//        } else System.out.println("Client non trouvé.");
//    }

//    public void searchClientByName() {
//
//        System.out.print("Entrez le nom du client à rechercher : ");
//        String nom = scanner.nextLine();
//
//        Optional<Client> existingClientOpt = clientService.getClientByName(nom);
//        if (existingClientOpt.isPresent()) {
//            System.out.println("Client trouvé : ");
//            System.out.println("Id : " + existingClientOpt.get().getId());
//            System.out.println("Nom : " + existingClientOpt.get().getNom());
//            System.out.println("Adresse : " + existingClientOpt.get().getAdresse());
//            System.out.println("Telephone : " + existingClientOpt.get().getTelephone());
//            System.out.println("is Professionnel : " + existingClientOpt.get().isProfessional());
//        } else System.out.println("Aucun client trouvé avec le nom " + nom);
//    }

    public void chercherClient() {
        System.out.println("--- Recherche de client existant ---");
        System.out.println("Entrez le nom du client : ");
        String name = scanner.nextLine();
        Client client = clientService.getClientByName(name);
        if (client != null) {
            System.out.println("No client found.");
        } else {
            System.out.println("Client trouvé !");
            System.out.println("Nom: " + client.getNom());
            System.out.println("Adresse: " + client.getAddress());
            System.out.println("Téléphone: " + client.getTelephone());
            System.out.println("Souhaitez-vous continuer avec ce client ? (y/n) : y");
            char choice = scanner.next().charAt(0);
            if (choice == 'y'){
                System.out.println("--- Création d'un Nouveau Projet ---");
                System.out.println("Entrez le nom du projet :");

            } else if (choice == 'n') {
                return;
            }
            System.out.println("Professionnel: " + client.isEstProfessionel());
            System.out.println("------------------------------");

        }
    }
}
