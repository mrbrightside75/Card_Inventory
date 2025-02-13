import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class DatabaseManager {
    private static final String URL = Config.get("DB_URL");
    private static final String USER = Config.get("DB_USER");
    private static final String PASSWORD = Config.get("DB_PASSWORD");

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
            return null;
        }
    }

    // ===========================
    // 🚀 INSERT METHODS (CREATE)
    // ===========================

    public static void insertPokemonCard(String cardName, String setCode, String collectorNumber, String rarity, int quantity, double price,
                                         String energyType, int hp, int retreatCost, String weakness, String resistance) {
        String sql = "INSERT INTO pokemon_cards (card_name, set_code, collector_number, rarity, quantity, price, energy_type, hp, retreat_cost, weakness, resistance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cardName);
            pstmt.setString(2, setCode);
            pstmt.setString(3, collectorNumber);
            pstmt.setString(4, rarity);
            pstmt.setInt(5, quantity);
            pstmt.setDouble(6, price);
            pstmt.setString(7, energyType);
            pstmt.setInt(8, hp);
            pstmt.setInt(9, retreatCost);
            pstmt.setString(10, weakness);
            pstmt.setString(11, resistance);

            pstmt.executeUpdate();
            System.out.println("✅ Pokémon card added: " + cardName);
        } catch (SQLException e) {
            System.err.println("❌ Error inserting Pokémon card: " + e.getMessage());
        }
    }

    public static void insertMagicCard(String cardName, String setCode, String collectorNumber, String rarity, int quantity, double price,
                                       String manaCost, String cardType, String power, String toughness, Integer loyalty) {
        String sql = "INSERT INTO magic_cards (card_name, set_code, collector_number, rarity, quantity, price, mana_cost, card_type, power, toughness, loyalty) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cardName);
            pstmt.setString(2, setCode);
            pstmt.setString(3, collectorNumber);
            pstmt.setString(4, rarity);
            pstmt.setInt(5, quantity);
            pstmt.setDouble(6, price);
            pstmt.setString(7, manaCost);
            pstmt.setString(8, cardType);
            pstmt.setString(9, power);
            pstmt.setString(10, toughness);
            if (loyalty != null) {
                pstmt.setInt(11, loyalty);
            } else {
                pstmt.setNull(11, java.sql.Types.INTEGER);
            }

            pstmt.executeUpdate();
            System.out.println("✅ Magic card added: " + cardName);
        } catch (SQLException e) {
            System.err.println("❌ Error inserting Magic card: " + e.getMessage());
        }
    }


    public static boolean isPokemonCard(String cardName) {
        String sql = "SELECT COUNT(*) FROM pokemon_cards WHERE card_name = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cardName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // Card is a Pokémon card
            }
        } catch (SQLException e) {
            System.err.println("❌ Error checking Pokémon card: " + e.getMessage());
        }
        return false;
    }

    public static boolean isMagicCard(String cardName) {
        String sql = "SELECT COUNT(*) FROM magic_cards WHERE card_name = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cardName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // Card is a Magic card
            }
        } catch (SQLException e) {
            System.err.println("❌ Error checking Magic card: " + e.getMessage());
        }
        return false;
    }


    // ===========================
    // 📜 LIST METHODS (READ)
    // ===========================

    public static ObservableList<TradingCard> getAllCards() {
        ObservableList<TradingCard> cards = FXCollections.observableArrayList();
        String sql = "SELECT card_name, set_code, rarity FROM pokemon_cards UNION SELECT card_name, set_code, rarity FROM magic_cards";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                cards.add(new TradingCard(rs.getString("card_name"), rs.getString("set_code"), rs.getString("rarity")));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching cards: " + e.getMessage());
        }
        return cards;
    }

    public static void listPokemonCards() {
        String sql = "SELECT * FROM pokemon_cards";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("=== Pokémon Card Inventory ===");
            while (rs.next()) {
                System.out.println("Card Name: " + rs.getString("card_name"));
                System.out.println("Set Code: " + rs.getString("set_code"));
                System.out.println("Collector Number: " + rs.getString("collector_number"));
                System.out.println("Rarity: " + rs.getString("rarity"));
                System.out.println("Quantity: " + rs.getInt("quantity"));
                System.out.println("Price: $" + rs.getDouble("price"));
                System.out.println("============================");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error retrieving Pokémon cards: " + e.getMessage());
        }
    }

    public static void listMagicCards() {
        String sql = "SELECT * FROM magic_cards";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("=== Magic: The Gathering Card Inventory ===");
            while (rs.next()) {
                System.out.println("Card Name: " + rs.getString("card_name"));
                System.out.println("Set Code: " + rs.getString("set_code"));
                System.out.println("Collector Number: " + rs.getString("collector_number"));
                System.out.println("Rarity: " + rs.getString("rarity"));
                System.out.println("Quantity: " + rs.getInt("quantity"));
                System.out.println("Price: $" + rs.getDouble("price"));
                System.out.println("============================");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error retrieving Magic cards: " + e.getMessage());
        }
    }

    // ===========================
    // ✏ UPDATE METHODS
    // ===========================

    public static void updatePokemonQuantity(String cardName, int newQuantity) {
        String sql = "UPDATE pokemon_cards SET quantity = ? WHERE card_name = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newQuantity);
            pstmt.setString(2, cardName);
            pstmt.executeUpdate();
            System.out.println("✅ Updated Pokémon card quantity: " + cardName);
        } catch (SQLException e) {
            System.err.println("❌ Error updating Pokémon card: " + e.getMessage());
        }
    }

    public static void updateMagicQuantity(String cardName, int newQuantity) {
        String sql = "UPDATE magic_cards SET quantity = ? WHERE card_name = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newQuantity);
            pstmt.setString(2, cardName);
            pstmt.executeUpdate();
            System.out.println("✅ Updated Magic card quantity: " + cardName);
        } catch (SQLException e) {
            System.err.println("❌ Error updating Magic card: " + e.getMessage());
        }
    }

    // ===========================
    // ❌ DELETE METHOD
    // ===========================

    public static void deleteCard(String game, String cardName) {
        String tableName = game.equalsIgnoreCase("Pokemon") ? "pokemon_cards" : "magic_cards";
        String sql = "DELETE FROM " + tableName + " WHERE card_name = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cardName);
            pstmt.executeUpdate();
            System.out.println("✅ Deleted " + game + " card: " + cardName);
        } catch (SQLException e) {
            System.err.println("❌ Error deleting card: " + e.getMessage());
        }
    }
}
