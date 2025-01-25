package repository;

import java.util.ArrayList;
import java.util.List;
import model.Appliance;

public class ApplianceRepositoryImpl implements ApplianceRepository<Appliance> {
    private final List<Appliance> appliances = new ArrayList<>(); // In-memory storage

    @Override
    public void add(Appliance appliance) {
        appliances.add(appliance);
    }

    @Override
    public void addAll(List<Appliance> appliances) {
        if (appliances != null) {
            this.appliances.addAll(appliances); // Add all appliances to the list
        }
    }

    @Override
    public List<Appliance> getAll() {
        return appliances; // Return the reference to ensure persistence of changes
    }

    @Override
    public List<Appliance> findByBrand(String brand) {
        List<Appliance> results = new ArrayList<>();
        for (Appliance appliance : appliances) {
            if (appliance.getBrand().equalsIgnoreCase(brand)) {
                results.add(appliance);
            }
        }
        return results;
    }

    @Override
    public <S extends Appliance> List<S> findByType(Class<S> type) {
        List<S> results = new ArrayList<>();
        for (Appliance appliance : appliances) {
            if (type.isInstance(appliance)) {
                results.add(type.cast(appliance)); // Cast to the specific type
            }
        }
        return results;
    }

    @Override
    public boolean decrementQuantity(String itemNumber) {
        for (Appliance appliance : appliances) {
            if (appliance.getItemNumber().equals(itemNumber)) { // Compare item numbers
                if (appliance.getQuantity() > 0) {
                    appliance.setQuantity(appliance.getQuantity() - 1);
                    System.out.println("DEBUG: Decremented quantity for item number: " + itemNumber + " to " + appliance.getQuantity());
                    return true; // Successfully decremented
                } else {
                    System.out.println("DEBUG: Quantity already zero for item number: " + itemNumber);
                    return false; // Quantity already zero
                }
            }
        }
        System.out.println("DEBUG: Item number not found: " + itemNumber);
        return false; // Item number not found
    }
}

