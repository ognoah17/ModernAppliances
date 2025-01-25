package integrationTest;

import org.junit.Test;
import controller.ApplianceController;
import service.ApplianceService;
import repository.ApplianceRepositoryImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class ControllerIntegrationTest {

    @Test
    public void testAllInputs() {
        String input = ""
                + "1\n143826003\n6\n"   // Check out a valid appliance
                + "1\n999999999\n6\n"   // Check out an invalid appliance
                + "2\nBosch\n6\n"       // Find appliances by brand: Bosch
                + "2\nTefal\n6\n"       // Find appliances by brand: Tefal
                + "3\n1\n2\n6\n"        // Display Refrigerators with 2 doors
                + "3\n1\n4\n6\n"        // Display Refrigerators with 4 doors
                + "3\n2\n18\n6\n"       // Display Vacuums with 18V battery
                + "3\n2\n24\n6\n"       // Display Vacuums with 24V battery
                + "3\n3\nK\n6\n"        // Display Microwaves for Kitchen
                + "3\n3\nW\n6\n"        // Display Microwaves for Work Site
                + "3\n4\nQt\n6\n"       // Display Dishwashers with Qt sound rating
                + "3\n4\nQr\n6\n"       // Display Dishwashers with Qr sound rating
                + "3\n4\nQu\n6\n"       // Display Dishwashers with Qu sound rating
                + "3\n4\nM\n6\n"        // Display Dishwashers with M sound rating
                + "4\n3\n6\n"           // Produce a random list of 3 appliances
                + "5\n6\n";             // Display all appliances

        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            // Set up input and output redirection
            System.setIn(inputStream);
            System.setOut(new PrintStream(outputStream));

            // Initialize controller with mock service and repository
            ApplianceRepositoryImpl repository = new ApplianceRepositoryImpl();
            ApplianceService service = new ApplianceService(repository);
            ApplianceController controller = new ApplianceController(service);

            // Start the application
            controller.start();

            // Restore the original System.out
            System.setOut(originalOut);

            // Capture and log the output
            String output = outputStream.toString();
            System.out.println("Captured Output:\n" + output);

            // Assertions for key parts of the output
            assertTrue("Expected checkout message not found.", 
                       output.contains("Appliance \"143826003\" has been successfully checked out."));
            assertTrue("Expected error for invalid item number not found.", 
                       output.contains("Error: No appliance found with the item number \"999999999\"."));
            assertTrue("Expected brand search result for Bosch not found.", 
                       output.contains("Matching Appliances:"));
            assertTrue("Expected no matches for invalid filters.", 
                       output.contains("No matching appliances found."));
        } finally {
            // Reset System.in and System.out
            System.setIn(System.in);
            System.setOut(originalOut);
        }
    }
}
