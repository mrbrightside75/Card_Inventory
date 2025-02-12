public class MTGCard extends Card {
    private String manaCost;
    private String cardType;
    private int power;
    private int toughness;
    private int loyalty; // Only used for Planeswalkers

    // Constructor for Creature and Spell cards
    public MTGCard(String cardName, String setCode, String collectorNumber, String rarity, int quantity, double price,
                   String manaCost, String cardType, int power, int toughness) {
        super(cardName, setCode, collectorNumber, rarity, quantity, price);
        this.manaCost = manaCost;
        this.cardType = cardType;
        this.power = power;
        this.toughness = toughness;
        this.loyalty = 0; // Default for non-planeswalkers
    }

    // Constructor for Planeswalkers
    public MTGCard(String cardName, String setCode, String collectorNumber, String rarity, int quantity, double price,
                   String manaCost, String cardType, int loyalty) {
        super(cardName, setCode, collectorNumber, rarity, quantity, price);
        this.manaCost = manaCost;
        this.cardType = cardType;
        this.power = 0; // Not applicable for Planeswalkers
        this.toughness = 0;
        this.loyalty = loyalty;
    }

    // Getters
    public String getManaCost() { return manaCost; }
    public String getCardType() { return cardType; }
    public int getPower() { return power; }
    public int getToughness() { return toughness; }
    public int getLoyalty() { return loyalty; }

    // Setters
    public void setManaCost(String manaCost) { this.manaCost = manaCost; }
    public void setCardType(String cardType) { this.cardType = cardType; }
    public void setPower(int power) { this.power = power; }
    public void setToughness(int toughness) { this.toughness = toughness; }
    public void setLoyalty(int loyalty) { this.loyalty = loyalty; }

    // Override displayCardInfo() to show Magic-specific attributes
    @Override
    public void displayCardInfo() {
        System.out.println("================================");
        System.out.println("Magic: The Gathering Card");
        System.out.println("Card Name: " + getCardName());
        System.out.println("Set Code: " + getSetCode());
        System.out.println("Collector Number: " + getCollectorNumber());
        System.out.println("Rarity: " + getRarity());
        System.out.println("Mana Cost: " + (manaCost != null ? manaCost : "N/A"));
        System.out.println("Card Type: " + cardType);

        if (power > 0 || toughness > 0) {
            System.out.println("Power/Toughness: " + power + "/" + toughness);
        }

        if (loyalty > 0) {
            System.out.println("Loyalty: " + loyalty);
        }

        System.out.println("Price: $" + getPrice());
        System.out.println("Quantity in Stock: " + getQuantity());
        System.out.println("================================");
    }
}
