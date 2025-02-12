public class Card {
    // Universal attributes
    private String cardName;
    private String setCode;
    private String collectorNumber;
    private String rarity;
    private int quantity;
    private double price;

    // Constructor (Now properly initializes fields)
    public Card(String cardName, String setCode, String collectorNumber, String rarity, int quantity, double price) {
        this.cardName = cardName;
        this.setCode = setCode;
        this.collectorNumber = collectorNumber;
        this.rarity = rarity;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters
    public String getCardName() { return cardName; }
    public String getSetCode() { return setCode; }
    public String getCollectorNumber() { return collectorNumber; }
    public String getRarity() { return rarity; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    // Setters
    public void setCardName(String cardName) { this.cardName = cardName; }
    public void setSetCode(String setCode) { this.setCode = setCode; }
    public void setCollectorNumber(String collectorNumber) { this.collectorNumber = collectorNumber; }
    public void setRarity(String rarity) { this.rarity = rarity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }

    // Display card details (to be overridden in subclasses if needed)
    public void displayCardInfo() {
        System.out.println("================================");
        System.out.println("Card Name: " + cardName);
        System.out.println("Set Code: " + setCode);
        System.out.println("Collector Number: " + collectorNumber);
        System.out.println("Rarity: " + rarity);
        System.out.println("Price: $" + price);
        System.out.println("Quantity in Stock: " + quantity);
        System.out.println("================================");
    }

    // Update stock quantity
    public void updateStock(int amount) {
        this.quantity += amount;
        System.out.println(cardName + " stock updated. New quantity: " + this.quantity);
    }
}
