package Config;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConfig {
    private static volatile DbConfig instance;
    private final Connection connection;
    private final Dotenv dotenv = Dotenv.configure().load();
    String dbPort = dotenv.get("DB_PORT");
    String dbName = dotenv.get("DB_NAME");
    String dbUserName = dotenv.get("DB_USERNAME");
    String dbPassword = dotenv.get("DB_PASSWORD");

    private final String url = "jdbc:postgresql://localhost:" + dbPort + "/" + dbName;
    private final String username = dbUserName;
    private final String password = dbPassword;

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

