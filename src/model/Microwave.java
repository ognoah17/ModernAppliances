package model;

public class Microwave extends Appliance {
    private double capacity; // Capacity in cubic feet
    private String roomType; // Kitchen (K) or Work Site (W)

    public Microwave(String itemNumber, String brand, int quantity, double price, String color, int wattage,
                     double capacity, String roomType) {
        super(itemNumber, brand, quantity, price, color, wattage);
        this.capacity = capacity;
        this.roomType = roomType;
    }

    // Getters and setters
    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Price: " + getPriceFormatted() + "\n" +
               "Color: " + getColor() + "\n" +
               "Wattage: " + getWattage() + "W\n" +
               "Item Number: " + getItemNumber() + "\n" +
               "Brand: " + getBrand() + "\n" +
               "Quantity: " + getQuantity() + "\n" +
               "Capacity: " + capacity + " cubic feet\n" +
               "Room Type: " + (roomType == null ? "Work Site" : 
                                (roomType.equalsIgnoreCase("K") ? "Kitchen" : "Work Site"));
    }
}
