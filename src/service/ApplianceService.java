package service;

import model.Appliance;
import repository.ApplianceRepository;
import util.FileService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ApplianceService {

    private final ApplianceRepository<Appliance> repository;

    // Constructor for dependency injection and file loading
    public ApplianceService(ApplianceRepository<Appliance> repository) {
        this.repository = repository;

        // Load appliances from file during initialization
        FileService fileService = new FileService("res/appliances.txt");
        List<Appliance> appliances = fileService.getAppliances();
        System.out.println("DEBUG: Appliances loaded from file: " + appliances.size() + " appliances found.");
        repository.addAll(appliances);
        System.out.println("DEBUG: Appliances added to repository.");
    }

    // Fetch all appliances
    public List<Appliance> fetchAllAppliances() {
        return repository.getAll();
    }

    // Fetch appliances by brand (case-insensitive)
    public List<Appliance> fetchAppliancesByBrand(String brand) {
        if (brand == null || brand.isEmpty()) {
            return Collections.emptyList();
        }
        return repository.getAll().stream()
                .filter(appliance -> appliance.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    // Fetch appliances by type
    public <T extends Appliance> List<T> fetchAppliancesByType(Class<T> type) {
        if (type == null) {
            return Collections.emptyList();
        }
        return repository.getAll().stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());
    }

    // Fetch a random list of appliances
    public List<Appliance> fetchRandomAppliances(int count) {
        List<Appliance> allAppliances = repository.getAll();
        if (allAppliances.isEmpty() || count <= 0) {
            return Collections.emptyList();
        }
        Collections.shuffle(allAppliances); // Randomize the order
        return allAppliances.subList(0, Math.min(count, allAppliances.size())); // Return up to 'count' appliances
    }

    // Display all appliances
    public void displayAllAppliances() {
        List<Appliance> allAppliances = repository.getAll();

        if (allAppliances.isEmpty()) {
            System.out.println("No appliances available.");
        } else {
            System.out.println("All appliances:");
            allAppliances.forEach(System.out::println);
        }
    }
    
    public boolean reduceQuantity(String itemNumber) {
        if (itemNumber == null || itemNumber.trim().isEmpty()) {
            System.out.println("DEBUG: Invalid item number input.");
            return false;
        }

        // Delegate to the repository to modify in-memory data
        boolean success = repository.decrementQuantity(itemNumber.trim());
        if (success) {
            // Save the updated appliances to the file
            FileService fileService = new FileService("res/appliances.txt");
            fileService.setAppliances(repository.getAll());
        }

        System.out.println("DEBUG: Reduce quantity for item number: " + itemNumber + " - " + (success ? "Success" : "Failed"));
        return success;
    }

    // Add a new appliance to the repository
    public void saveAppliance(Appliance appliance) {
        if (appliance != null) {
            repository.add(appliance);
        }
    }
}
