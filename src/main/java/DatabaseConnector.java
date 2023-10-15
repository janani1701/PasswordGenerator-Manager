import java.sql.*;

public class DatabaseConnector {
    private static final String DB_URL = "jdbc:sqlite:sqlite.db";

    static {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS passwords (website TEXT, password TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void savePassword(String website, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String existingPassword = getPassword(website);

            if (existingPassword != null) {
                try (PreparedStatement stmt = conn.prepareStatement("UPDATE passwords SET password = ? WHERE website = ?")) {
                    stmt.setString(1, password);
                    stmt.setString(2, website);
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO passwords (website, password) VALUES (?, ?)")) {
                    stmt.setString(1, website);
                    stmt.setString(2, password);
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getPassword(String website) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement("SELECT password FROM passwords WHERE website = ?")) {
            stmt.setString(1, website);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
