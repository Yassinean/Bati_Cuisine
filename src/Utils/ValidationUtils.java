package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ValidationUtils {

    private static final Scanner scanner = new Scanner(System.in);

    // Validate non-empty string input
    public static String validateStringInput(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                break;
            } else {
                System.out.println("Erreur : le champ ne peut pas être vide. Veuillez réessayer.");
            }
        }
        return input;
    }

    // Validate double input (positive)
    public static double validateDoubleInput(String prompt) {
        double input;
        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextDouble();
                scanner.nextLine(); // Clear buffer
                if (input > 0) {
                    break;
                } else {
                    System.out.println("Erreur : veuillez entrer un nombre supérieur à zéro.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : entrée invalide. Veuillez entrer un nombre.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        return input;
    }

    // Validate int input (positive)
    public static int validateIntInput(String prompt) {
        int input;
        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextInt();
                scanner.nextLine();
                if (input > 0) {
                    break;
                } else {
                    System.out.println("Erreur : veuillez entrer un nombre supérieur à zéro.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : entrée invalide. Veuillez entrer un nombre.");
                scanner.nextLine();
            }
        }
        return input;
    }

    // Validate percentage (0 - 100)
    public static double validatePercentageInput(String prompt) {
        double input;
        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextDouble();
                scanner.nextLine(); // Clear buffer
                if (input >= 0 && input <= 100) {
                    break;
                } else {
                    System.out.println("Erreur : veuillez entrer un pourcentage valide (0-100).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : entrée invalide. Veuillez entrer un nombre.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        return input;
    }

    // Validate date input
    public static LocalDate validateDateInput(String prompt) {
        LocalDate date;
        while (true) {
            System.out.print(prompt);
            String dateInput = scanner.nextLine();
            try {
                date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Erreur : format de date invalide. Veuillez utiliser le format jj/mm/aaaa.");
            }
        }
        return date;
    }

    // Validate yes/no input
    public static boolean validateYesNoInput(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("n")) {
                return input.equals("y");
            } else {
                System.out.println("Erreur : veuillez entrer 'y' pour oui ou 'n' pour non.");
            }
        }
    }
}
