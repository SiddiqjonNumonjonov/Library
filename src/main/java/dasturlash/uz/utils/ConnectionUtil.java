package dasturlash.uz.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Library", "userjon", "12345");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
