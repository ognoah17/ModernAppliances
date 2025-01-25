package util;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    private final String filePath;
    private final List<Appliance> appliances;

    // Constructor to accept a file path
    public FileService(String filePath) {
        this.filePath = filePath;
        this.appliances = new ArrayList<>();
        try {
            parseFile(); // Parse the file when the service is created
        } catch (IOException e) {
            System.err.println("Error initializing FileService: " + e.getMessage());
        }
    }

    // Parses the file and stores appliances in the list
    private void parseFile() throws IOException {
        File file = new File(filePath);
        if (!file.exists() || !file.canRead()) {
            throw new FileNotFoundException("File not found or cannot be read: " + filePath);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parseLine(line); // Parse each line into an appliance object
            }
        }
    }

    // Parse a single line and add the corresponding appliance
    private void parseLine(String line) {
        try {
            String[] data = line.split(";");
            String itemNumber = data[0];
            String brand = data[1];
            int quantity = Integer.parseInt(data[2]);
            int wattage = Integer.parseInt(data[3]);
            String color = data[4];
            double price = Double.parseDouble(data[5]);

            if (itemNumber.startsWith("1")) { // Refrigerator
                appliances.add(new Refrigerator(
                        itemNumber, brand, quantity, wattage, color, price,
                        Integer.parseInt(data[6]), // Number of doors
                        Double.parseDouble(data[7]), // Height
                        Double.parseDouble(data[8])  // Width
                ));
            } else if (itemNumber.startsWith("2")) { // Vacuum
                appliances.add(new Vacuum(
                        itemNumber, brand, quantity, price, color, wattage,
                        data[6], // Grade
                        Integer.parseInt(data[7]) // Battery voltage
                ));
            } else if (itemNumber.startsWith("3")) { // Microwave
                appliances.add(new Microwave(
                        itemNumber, brand, quantity, price, color, wattage,
                        Double.parseDouble(data[6]), // Capacity
                        data[7]  // Room type
                ));
            } else if (itemNumber.startsWith("4") || itemNumber.startsWith("5")) { // Dishwasher
                appliances.add(new Dishwasher(
                        itemNumber, brand, quantity, price, color, wattage,
                        data[6].trim(), // Sound rating
                        data[7].trim()  // Feature
               ));

            } else {
                System.err.println("Unknown appliance type skipped: " + line);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Malformed data skipped: " + line);
        }
    }

    // Returns the parsed appliances
    public List<Appliance> getAppliances() {
        return new ArrayList<>(appliances); // Return a copy to avoid external modifications
    }

    // Replaces the current list with the new list and writes to file
    public void setAppliances(List<Appliance> updatedAppliances) {
        appliances.clear();
        appliances.addAll(updatedAppliances);
        writeAppliances();
    }

    // Writes the list of appliances back to the file
    public void writeAppliances() {
        File file = new File(filePath);
        if (!file.exists() || !file.canWrite()) {
            System.err.println("File not found or cannot be written to: " + filePath);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Appliance appliance : appliances) {
                writer.write(formatAppliance(appliance));
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Format an appliance into a string for writing to a file
    private String formatAppliance(Appliance appliance) {
        if (appliance instanceof Refrigerator) {
            Refrigerator fridge = (Refrigerator) appliance;
            return String.format("%s;%s;%d;%d;%s;%.2f;%d;%.2f;%.2f%n",
                    fridge.getItemNumber(),
                    fridge.getBrand(),
                    fridge.getQuantity(),
                    fridge.getWattage(),
                    fridge.getColor(),
                    fridge.getPrice(),
                    fridge.getNumberOfDoors(),
                    fridge.getHeight(),
                    fridge.getWidth()
            );
        } else if (appliance instanceof Vacuum) {
            Vacuum vacuum = (Vacuum) appliance;
            return String.format("%s;%s;%d;%d;%s;%.2f;%s;%d%n",
                    vacuum.getItemNumber(),
                    vacuum.getBrand(),
                    vacuum.getQuantity(),
                    vacuum.getWattage(),
                    vacuum.getColor(),
                    vacuum.getPrice(),
                    vacuum.getGrade(),
                    vacuum.getBatteryVoltage()
            );
        } else if (appliance instanceof Microwave) {
            Microwave microwave = (Microwave) appliance;
            return String.format("%s;%s;%d;%d;%s;%.2f;%.2f;%s%n",
                    microwave.getItemNumber(),
                    microwave.getBrand(),
                    microwave.getQuantity(),
                    microwave.getWattage(),
                    microwave.getColor(),
                    microwave.getPrice(),
                    microwave.getCapacity(),
                    microwave.getRoomType()
            );
        } else if (appliance instanceof Dishwasher) {
            Dishwasher dishwasher = (Dishwasher) appliance;
            return String.format("%s;%s;%d;%d;%s;%.2f;%s;%s%n",
                    dishwasher.getItemNumber(),
                    dishwasher.getBrand(),
                    dishwasher.getQuantity(),
                    dishwasher.getWattage(),
                    dishwasher.getColor(),
                    dishwasher.getPrice(),
                    dishwasher.getFeature(),
                    dishwasher.getSoundRating()
            );
        }
        return "";
    }
}
