package model;

public class Vacuum extends Appliance {
    private String grade; // Residential or Commercial
    private int batteryVoltage; // 18V (Low) or 24V (High)

    public Vacuum(String itemNumber, String brand, int quantity, double price, String color, int wattage,
                  String grade, int batteryVoltage) {
        super(itemNumber, brand, quantity, price, color, wattage);
        this.grade = grade;
        this.batteryVoltage = batteryVoltage;
    }

    // Getters and setters
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(int batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    @Override
    public String toString() {
        return "Price: " + getPriceFormatted() + "\n" +
               "Color: " + getColor() + "\n" +
               "Wattage: " + getWattage() + "W\n" +
               "Item Number: " + getItemNumber() + "\n" +
               "Brand: " + getBrand() + "\n" +
               "Quantity: " + getQuantity() + "\n" +
               "Grade: " + grade + "\n" +
               "Battery Voltage: " + (batteryVoltage == 18 ? "Low (18V)" : "High (24V)");
    }
}
