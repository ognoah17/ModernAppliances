package main;

import controller.ApplianceController; 
import repository.ApplianceRepositoryImpl;
import service.ApplianceService;

public class Main {
    public static void main(String[] args) {
        ApplianceRepositoryImpl repository = new ApplianceRepositoryImpl();
        ApplianceService service = new ApplianceService(repository);
        ApplianceController controller = new ApplianceController(service);
        controller.start();
    }
}
 