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

    public static void listCards() {
        String sql = "SELECT * FROM cards";

        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("card_name"));
                System.out.println("Set Code: " + rs.getString("set_code"));
                System.out.println("Collector Number: " + rs.getString("collector_number"));
                System.out.println("Rarity: " + rs.getString("rarity"));
                System.out.println("Quantity: " + rs.getInt("quantity"));
                System.out.println("Price: $" + rs.getDouble("price"));
                System.out.println("====================================");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error retrieving cards: " + e.getMessage());
        }
    }

    public static void inventoryTotal() {
        String sql = "SELECT SUM(price * quantity) AS total_value FROM cards";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                double total = rs.getDouble("total_value");
                System.out.println("💰 Total Inventory Value: $" + total);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error calculating total inventory value: " + e.getMessage());
        }
    }

}
