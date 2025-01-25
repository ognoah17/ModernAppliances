package model;

public class Dishwasher extends Appliance {
    private String soundRating; // Qt, Qr, Qu, or M
    private String feature;     // Additional feature (e.g., "Third Rack")

    public Dishwasher(String itemNumber, String brand, int quantity, double price, String color, int wattage,
                      String soundRating, String feature) {
        super(itemNumber, brand, quantity, price, color, wattage);
        this.soundRating = soundRating;
        this.feature = feature;
    }

    // Getters and setters
    public String getSoundRating() {
        return soundRating;
    }

    public void setSoundRating(String soundRating) {
        this.soundRating = soundRating;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    @Override
    public String toString() {
        return "Price: " + getPriceFormatted() + "\n" +
               "Color: " + getColor() + "\n" +
               "Wattage: " + getWattage() + "W\n" +
               "Item Number: " + getItemNumber() + "\n" +
               "Brand: " + getBrand() + "\n" +
               "Quantity: " + getQuantity() + "\n" +
               "Feature: " + feature + "\n" +
               "Sound Rating: " + getSoundRatingDescription();
    }

    // Helper method to provide a description of the sound rating
    private String getSoundRatingDescription() {
        switch (soundRating) {
            case "Qt": return "Quietest";
            case "Qr": return "Quieter";
            case "Qu": return "Quiet";
            case "M": return "Moderate";
            default: return "Unknown";
        }
    }
}
