package repository;

import java.util.List;
import model.Appliance;

public interface ApplianceRepository<T extends Appliance> {
    void add(T appliance);                    // Store parsed appliances
    void addAll(List<T> appliances);          // Add multiple appliances
    List<T> getAll();                         // Retrieve all appliances
    List<T> findByBrand(String brand);        // Find appliances by brand (case-insensitive)
    <S extends T> List<S> findByType(Class<S> type); // Find appliances by subclass type
    boolean decrementQuantity(String itemNumber); // Decrement quantity by item number
}
