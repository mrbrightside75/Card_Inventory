import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CardUI {
    private final TextField cardNameField = new TextField();
    private final TextField setCodeField = new TextField();
    private final TextField collectorNumberField = new TextField();
    private final TextField rarityField = new TextField();
    private final ChoiceBox<String> gameChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList("Pokemon", "Magic"));
    private final TableView<TradingCard> cardTable = new TableView<>();
    private final ObservableList<TradingCard> cardData = FXCollections.observableArrayList();

    public VBox getView() {
        HBox nameBox = createHBox("Card Name:", cardNameField);
        HBox setBox = createHBox("Set Code:", setCodeField);
        HBox collectorBox = createHBox("Collector Number:", collectorNumberField);
        HBox rarityBox = createHBox("Rarity:", rarityField);
        HBox gameBox = createHBox("Game:", gameChoiceBox);

        Button addButton = new Button("Add Card");
        Button deleteButton = new Button("Delete Card");

        addButton.setOnAction(event -> addCard());
        deleteButton.setOnAction(event -> deleteCard());

        TableColumn<TradingCard, String> nameColumn = new TableColumn<>("Card Name");
        nameColumn.setCellValueFactory(data -> data.getValue().cardNameProperty());

        TableColumn<TradingCard, String> setColumn = new TableColumn<>("Set Code");
        setColumn.setCellValueFactory(data -> data.getValue().setCodeProperty());

        TableColumn<TradingCard, String> rarityColumn = new TableColumn<>("Rarity");
        rarityColumn.setCellValueFactory(data -> data.getValue().rarityProperty());

        cardTable.getColumns().addAll(nameColumn, setColumn, rarityColumn);
        cardTable.setItems(cardData);

        refreshTable();

        return new VBox(10, nameBox, setBox, collectorBox, rarityBox, gameBox, addButton, deleteButton, cardTable);
    }

    private HBox createHBox(String labelText, Control field) {
        Label label = new Label(labelText);
        return new HBox(10, label, field);
    }

    private void addCard() {
        String game = gameChoiceBox.getValue();
        String cardName = cardNameField.getText();
        String setCode = setCodeField.getText();
        String collectorNumber = collectorNumberField.getText();
        String rarity = rarityField.getText();

        if (game.equals("Pokemon")) {
            DatabaseManager.insertPokemonCard(cardName, setCode, collectorNumber, rarity, 1, 10.00, "Fire", 100, 2, "Water", "None");
        } else {
            DatabaseManager.insertMagicCard(cardName, setCode, collectorNumber, rarity, 1, 10.00, "{2}{R}", "Creature - Goblin", "2", "2", null);
        }

        refreshTable();
    }

    private String determineGameType(String cardName) {
        if (DatabaseManager.isPokemonCard(cardName)) {
            return "Pokemon";
        } else if (DatabaseManager.isMagicCard(cardName)) {
            return "Magic";
        }
        return "Unknown";
    }

    private void deleteCard() {
        TradingCard selectedCard = cardTable.getSelectionModel().getSelectedItem();
        if (selectedCard != null) {
            String gameType = determineGameType(selectedCard.getCardName()); // Get the game type
            DatabaseManager.deleteCard(gameType, selectedCard.getCardName()); // Pass both arguments
            refreshTable();
        }
    }


    private void refreshTable() {
        cardData.clear();
        cardData.addAll(DatabaseManager.getAllCards());
    }
}
