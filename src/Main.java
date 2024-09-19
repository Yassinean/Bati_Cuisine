import Repository.Implementation.ClientImp;
import Model.Client;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClientImp clientImp = new ClientImp();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Client");
            System.out.println("2. View All Clients");
            System.out.println("3. Find Client by ID");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Client Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter Telephone: ");
                    String telephone = scanner.nextLine();
                    System.out.print("Enter Etat Client is Professional(e.g., true,false): ");
                    boolean stateInput = scanner.nextBoolean();

                    Client newClient = new Client(1, name, address, telephone, stateInput);
                    clientImp.addClient(newClient);
                    System.out.println("Client added successfully!");
                    break;

                case 2:
                    List<Client> allClients = clientImp.findAllClients();
                    System.out.println("All Clients:");
                    for (Client client : allClients) {
                        System.out.println(client);
                    }
                    break;

                case 3:
                    System.out.print("Enter client ID: ");
                    int id = scanner.nextInt();
                    Client projectById = clientImp.findClientById(id);
                    if (projectById != null) {
                        System.out.println("Client Found: " + projectById);
                    } else {
                        System.out.println("Client not found.");
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
