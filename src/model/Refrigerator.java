package model;

// The Refrigerator class extends the Appliance class
public class Refrigerator extends Appliance {
    // Specific attributes for refrigerators
    private int numberOfDoors; // 2, 3, or 4 doors
    private double height;     // Height in inches
    private double width;      // Width in inches

 // Constructor to initialize attributes, including those from the Appliance class
    public Refrigerator(String itemNumber, String brand, int quantity, int wattage, String color, double price, 
                        int numberOfDoors, double height, double width) {
        super(itemNumber, brand, quantity, price, color, wattage); // Call the Appliance constructor with int wattage
        this.numberOfDoors = numberOfDoors;
        this.height = height;
        this.width = width;
    }


    // Getters and setters for Refrigerator-specific attributes
    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    // Override the toString() method to include Refrigerator-specific attributes
    @Override
    public String toString() {
        return "Price: " + getPriceFormatted() + "\n" + // Use the formatted price
               "Color: " + getColor() + "\n" +
               "Wattage: " + getWattage() + "W\n" +
               "Item Number: " + getItemNumber() + "\n" +
               "Brand: " + getBrand() + "\n" +
               "Quantity: " + getQuantity() + "\n" +
               "Number of Doors: " + numberOfDoors + "\n" +
               "Height: " + height + " inches\n" +
               "Width: " + width + " inches";
    }
}
