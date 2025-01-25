package controller;

import model.Microwave;
import model.Refrigerator;
import model.Vacuum;
import org.junit.Before;
import org.junit.Test;
import repository.InMemoryApplianceRepository;
import service.ApplianceService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertFalse;

public class ApplianceControllerTest {

    private ApplianceController controller;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUp() {
        System.out.println("Setting up test environment...");

        // Initialize ApplianceRepository and ApplianceService
        InMemoryApplianceRepository repository = new InMemoryApplianceRepository();
        ApplianceService service = new ApplianceService(repository);
        controller = new ApplianceController(service);

        // Add sample appliances to the repository with corrected wattage
        Refrigerator refrigerator = new Refrigerator("R123", "BrandA", 2, 1200, "White", 200.00, 2, 70, 35);
        Vacuum vacuum = new Vacuum("V456", "BrandB", 3, 350.00, "Black", 50, "Residential", 18);
        Microwave microwave = new Microwave("M789", "BrandA", 5, 250.00, "Silver", 1000, 2.0, "K");

        // Add appliances to the repository
        repository.add(refrigerator);
        repository.add(vacuum);
        repository.add(microwave);

        // Print sample appliances for verification
        System.out.println("Sample appliances added:");
        System.out.println(refrigerator);
        System.out.println(vacuum);
        System.out.println(microwave);

        // Redirect System.out to capture output
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testDisplayAllAppliances() {
        System.out.println("Running test: testDisplayAllAppliances");

        // Act: Call the method to display all appliances
        controller.displayAllAppliances();

        // Capture the output
        String output = outContent.toString();
        System.out.println("Captured Output: " + output);

        // Verify that at least one appliance is displayed
        assertFalse("Expected output to display appliances, but it was empty.", output.isEmpty());
    }
}
