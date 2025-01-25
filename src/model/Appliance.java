package model;

import java.text.DecimalFormat;

public abstract class Appliance {
    // Shared attributes for all appliances
    private String itemNumber;
    private String brand;
    private int quantity;
    private double price;
    private String color;
    private int wattage;

 // Corrected Constructor
    public Appliance(String itemNumber, String brand, int quantity, double price, String color, int wattage) {
        this.itemNumber = itemNumber;
        this.brand = brand;
        this.quantity = quantity;
        this.price = price;
        this.color = color;
        this.wattage = wattage; // Assigning the correct int type
    }


    // Getters and Setters
    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceFormatted() {
        DecimalFormat decimalFormat = new DecimalFormat("$###0.00");
        return decimalFormat.format(getPrice());
    }
    
    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWattage() {
        return wattage;
    }

    public void setWattage(int wattage) {
        this.wattage = wattage;
    }

    // Method to decrement quantity
    public boolean purchase() {
        if (quantity > 0) {
            quantity--;
            return true;
        } else {
            return false; // Out of stock
        }
    }

    // toString() implementation for shared attributes
    @Override
    public String toString() {
        return "Item Number: " + itemNumber + "\n" +
               "Brand: " + brand + "\n" +
               "Quantity: " + quantity + "\n" +
               "Price: " + getPriceFormatted() + "\n" +
               "Color: " + color + "\n" +
               "Wattage: " + wattage + "W";
    }
}
