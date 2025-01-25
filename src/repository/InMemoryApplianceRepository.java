package repository;

import model.Appliance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryApplianceRepository implements ApplianceRepository<Appliance> {
    private final List<Appliance> appliances = new ArrayList<>();

    @Override
    public List<Appliance> getAll() {
        return new ArrayList<>(appliances);
    }

    @Override
    public List<Appliance> findByBrand(String brand) {
        return appliances.stream()
                .filter(appliance -> appliance.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }


    @Override
    public <T extends Appliance> List<T> findByType(Class<T> type) {
        return appliances.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());
    }

    @Override
    public boolean decrementQuantity(String itemNumber) {
        for (Appliance appliance : appliances) {
            if (appliance.getItemNumber().equals(itemNumber) && appliance.getQuantity() > 0) {
                appliance.setQuantity(appliance.getQuantity() - 1);
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(Appliance appliance) {
        appliances.add(appliance);
    }

	@Override
	public void addAll(List<Appliance> appliances) {
		// TODO Auto-generated method stub
		
	}
}
