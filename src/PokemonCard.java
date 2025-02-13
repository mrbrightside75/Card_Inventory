public class PokemonCard extends Card{
    private String energyType;
    private int hp;
    private int retreatCost;
    private String weakness;
    private String resistance;

    public PokemonCard(String cardName, String setCode, String collectorNumber, String rarity, int quantity, double price, String energyType, int hp, int retreatCost, String weakness, String resistance) {
        super(cardName, setCode, collectorNumber, rarity, quantity, price);
        this.energyType = energyType;
        this.hp = hp;
        this.retreatCost = retreatCost;
        this.weakness = weakness;
        this.resistance = resistance;
    }

//    Getters and Setters
    public String getEnergyType() {return energyType;}
    public void setEnergyType(String energyType) {this.energyType = energyType;}
    public int getHp() {return hp;}
    public void setHp(int hp) {this.hp = hp;}
    public int getRetreatCost() {return retreatCost;}
    public void setRetreatCost(int retreatCost) {this.retreatCost = retreatCost;}
    public String getWeakness() {return weakness;}
    public void setWeakness(String weakness) {this.weakness = weakness;}
    public String getResistance() {return resistance;}
    public void setResistance(String resistance) {this.resistance = resistance;}

    @Override
    public void displayCardInfo(){
        System.out.println("================================");
        System.out.println("Pokemon Card Information");
        System.out.println("Card Name: " + getCardName());
        System.out.println("Set Code: " + getSetCode());
        System.out.println("Collector Number: " + getCollectorNumber());
        System.out.println("Rarity: " + getRarity());
        System.out.println("Energy Type: " + getEnergyType());
        System.out.println("HP: " + getHp());
        System.out.println("Retreat Cost: " + getRetreatCost());
        System.out.println("Weakness: " + getWeakness());
        System.out.println("Resistance: " + getResistance());
        System.out.println("Price: $" + getPrice());
        System.out.println("Quantity in Stock: " + getQuantity());
        System.out.println("================================");
    }

}
