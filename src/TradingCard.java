import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TradingCard {
    private final StringProperty cardName;
    private final StringProperty setCode;
    private final StringProperty rarity;

    public TradingCard(String cardName, String setCode, String rarity) {
        this.cardName = new SimpleStringProperty(cardName);
        this.setCode = new SimpleStringProperty(setCode);
        this.rarity = new SimpleStringProperty(rarity);
    }

    public StringProperty cardNameProperty() {
        return cardName;
    }

    public StringProperty setCodeProperty() {
        return setCode;
    }

    public StringProperty rarityProperty() {
        return rarity;
    }

    public String getCardName() {
        return cardName.get();
    }

    public String getSetCode() {
        return setCode.get();
    }

    public String getRarity() {
        return rarity.get();
    }
}
