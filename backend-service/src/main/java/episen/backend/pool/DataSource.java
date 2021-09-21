package episen.backend.pool;

import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class  DataSource {

    private static JDBCConnectionPool pool;

    public DataSource(int size, Properties prop) {
        try {
            pool = new JDBCConnectionPool(prop);
            pool.turnConnection(size);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return pool.retrieveConnection();
    }

    public static void addConnection(Connection con) {
        pool.removeConnection(con);
    }

    public static void closeAllConnection() {
        pool.closeConnection();
    }
}
