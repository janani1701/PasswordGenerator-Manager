import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.sql.*;
import java.security.SecureRandom;
import java.util.Scanner;

public class Main {
    private static String SECURITY_CODE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (SECURITY_CODE == null) {
            System.out.print("Set your security code (4-digit number): ");
            SECURITY_CODE = scanner.nextLine();
            while (SECURITY_CODE.length() != 4 || !SECURITY_CODE.matches("\\d+")) {
                System.out.println("Invalid security code. Please enter a 4-digit number.");
                SECURITY_CODE = scanner.nextLine();
            }
        }

        while (true) {
            System.out.println("Options:");
            System.out.println("1. Generate new password and save");
            System.out.println("2. View saved password");
            System.out.println("3. Change password for saved websites");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the website for the password: ");
                    String website = scanner.nextLine();
                    System.out.print("Enter the password length: ");
                    int length = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    String password = PasswordGenerator.generatePassword(length);
                    DatabaseConnector.savePassword(website, password);
                    System.out.println("Generated Password: " + password);
                    break;
                case 2:
                    System.out.print("Enter the website for the password: ");
                    website = scanner.nextLine();
                    String savedPassword = DatabaseConnector.getPassword(website);
                    if (savedPassword != null) {
                        System.out.println("Password for " + website + ": " + savedPassword);
                    } else {
                        System.out.println("Website not found in the database.");
                        System.out.print("Do you want to generate a new password and save? (Y/N): ");
                        String response = scanner.nextLine();

                        if (response.equalsIgnoreCase("Y")) {
                            System.out.print("Enter the password length: ");
                            length = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            password = PasswordGenerator.generatePassword(length);
                            DatabaseConnector.savePassword(website, password);
                            System.out.println("Generated Password: " + password);
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter the security code: ");
                    String enteredCode = scanner.nextLine();

                    if (enteredCode.equals(SECURITY_CODE)) {
                        System.out.print("Enter the website for which you want to change the password: ");
                        website = scanner.nextLine();
                        System.out.print("Enter the new password: ");
                        String newPassword = scanner.nextLine();
                        DatabaseConnector.savePassword(website, newPassword);
                        System.out.println("Password changed for " + website);
                    } else {
                        System.out.println("Invalid security code. Access denied.");
                    }
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
