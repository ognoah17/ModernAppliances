package controller;

import model.Appliance;
import model.Dishwasher;
import model.Microwave;
import model.Refrigerator;
import model.Vacuum;
import service.ApplianceService;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ApplianceController {

    private final ApplianceService applianceService;

    // Constructor for dependency injection
    public ApplianceController(ApplianceService applianceService) {
        this.applianceService = applianceService;
    }

    // Start the console application
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nWelcome to Modern Appliances!");
            System.out.println("How may we assist you?");
            System.out.println("1 – Check out appliance");
            System.out.println("2 – Find appliances by brand");
            System.out.println("3 – Display appliances by type");
            System.out.println("4 – Produce random appliance list");
            System.out.println("5 – Display all appliances");
            System.out.println("6 – Save & exit"); // This system auto-saves the data to the file immediately
            System.out.print("Enter option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    checkOutAppliance(scanner);
                    break;
                case 2:
                    findAppliancesByBrand(scanner);
                    break;
                case 3:
                    displayAppliancesByType(scanner);
                    break;
                case 4:
                    produceRandomApplianceList(scanner);
                    break;
                case 5:
                    displayAllAppliances();
                    break;
                case 6:
                    saveAndExit();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private void checkOutAppliance(Scanner scanner) {
        System.out.print("Enter the item number of an appliance: ");
        String itemNumber = scanner.nextLine();

        // Validate input
        if (itemNumber == null || itemNumber.trim().isEmpty()) {
            System.out.println("Error: Item number cannot be empty.");
            return;
        }

        // Check if the item exists and handle errors
        boolean itemExists = applianceService.fetchAllAppliances().stream()
                .anyMatch(appliance -> appliance.getItemNumber().equals(itemNumber.trim()));
        if (!itemExists) {
            System.out.println("Error: No appliance found with the item number \"" + itemNumber + "\".");
            return;
        }

        // Attempt to reduce the quantity
        boolean success = applianceService.reduceQuantity(itemNumber.trim());
        if (success) {
            System.out.println("Appliance \"" + itemNumber + "\" has been successfully checked out.");
        } else {
            // Specific error: Quantity is zero
            System.out.println("Error: Appliance \"" + itemNumber + "\" is out of stock and cannot be checked out.");
        }
    }

    public void findAppliancesByBrand(Scanner scanner) {
        System.out.print("Enter brand to search for: ");
        String brand = scanner.nextLine();
        List<Appliance> appliances = applianceService.fetchAppliancesByBrand(brand);

        if (appliances.isEmpty()) {
            System.out.println("No appliances found for the brand: " + brand);
        } else {
            System.out.println("Matching Appliances:");
            appliances.forEach(System.out::println);
        }
    }

    private void displayAppliancesByType(Scanner scanner) {
        System.out.println("Appliance Types");
        System.out.println("1 – Refrigerators");
        System.out.println("2 – Vacuums");
        System.out.println("3 – Microwaves");
        System.out.println("4 – Dishwashers");
        System.out.print("Enter type of appliance: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<? extends Appliance> appliances;

        switch (choice) {
            case 1: // Refrigerators
                System.out.print("Enter the number of doors (2, 3, or 4): ");
                int doors = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                appliances = applianceService.fetchAppliancesByType(Refrigerator.class).stream()
                        .filter(refrigerator -> ((Refrigerator) refrigerator).getNumberOfDoors() == doors)
                        .collect(Collectors.toList());
                break;

            case 2: // Vacuums
                System.out.print("Enter battery voltage (18 for Low, 24 for High): ");
                int voltage = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                appliances = applianceService.fetchAppliancesByType(Vacuum.class).stream()
                        .filter(vacuum -> ((Vacuum) vacuum).getBatteryVoltage() == voltage)
                        .collect(Collectors.toList());
                break;

            case 3: // Microwaves
                System.out.print("Enter room type (K for Kitchen, W for Work Site): ");
                String roomType = scanner.nextLine().trim().toUpperCase();
                appliances = applianceService.fetchAppliancesByType(Microwave.class).stream()
                        .filter(microwave -> ((Microwave) microwave).getRoomType().equalsIgnoreCase(roomType))
                        .collect(Collectors.toList());
                break;

            case 4: // Dishwashers
                System.out.print("Enter sound rating (Qt, Qr, Qu, or M): ");
                String soundRating = scanner.nextLine().trim();
                appliances = applianceService.fetchAppliancesByType(Dishwasher.class).stream()
                        .filter(dishwasher -> ((Dishwasher) dishwasher).getSoundRating().equalsIgnoreCase(soundRating))
                        .collect(Collectors.toList());
                break;

            default:
                System.out.println("Invalid type selected.");
                return;
        }

        if (appliances.isEmpty()) {
            System.out.println("No matching appliances found.");
        } else {
            System.out.println("Matching appliances:");
            appliances.forEach(System.out::println);
        }
    }


    private void produceRandomApplianceList(Scanner scanner) {
        System.out.print("Enter the number of random appliances to display: ");
        int count = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<Appliance> randomAppliances = applianceService.fetchRandomAppliances(count);

        if (randomAppliances.isEmpty()) {
            System.out.println("No appliances available.");
        } else {
            System.out.println("Random appliances:");
            randomAppliances.forEach(System.out::println);
        }
    }

    // Display all appliances in the repository
    void displayAllAppliances() {
        List<Appliance> allAppliances = applianceService.fetchAllAppliances();

        if (allAppliances.isEmpty()) {
            System.out.println("No appliances available.");
            return;
        }

        System.out.println("All appliances:");
        allAppliances.forEach(System.out::println);
    }

    private void saveAndExit() {
        System.out.println("Saving data and exiting...");
    }
}
