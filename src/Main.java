public class Main {
    public static void main(String[] args) {
        DatabaseManager.listCards();

        System.out.println("============================");
        System.out.println("GATHERING INVENTORY TOTAL");
        System.out.println("============================");

        DatabaseManager.inventoryTotal();

        System.out.println("Database URL: " + Config.get("DB_URL"));
        System.out.println("Database User: " + Config.get("DB_USER"));
        System.out.println("Database Password: " + Config.get("DB_PASSWORD"));
    }
}
