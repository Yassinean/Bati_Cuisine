package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {
    private static volatile DbConfig instance;
    private final Connection connection;
    private final String url = "jdbc:postgresql://localhost:5432/BatiCuisine";
    private final String username = "postgres";
    private final String password = "hanach";

    private DbConfig() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new SQLException("Impossible de se connecter à la base de données", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DbConfig getInstance() throws SQLException {
        if (instance == null) {
            synchronized (DbConfig.class) {
                if (instance == null) {
                    instance = new DbConfig();
                }
            }
        } else if (instance.getConnection().isClosed()) {
            instance = new DbConfig();
        }
        return instance;
    }
}

