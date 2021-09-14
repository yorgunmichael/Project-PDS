package episen.backend.pool;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class JDBCConnectionPool {

    private static ArrayList<Connection> myConnection = new ArrayList<>();
    private Properties props;

    public JDBCConnectionPool(Properties props) throws IOException {
        this.props = props;
        try {
            Class.forName(props.getProperty("jdbc.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection connectionFactory() {
        Connection con = null;

        try {
            String url = props.getProperty("jdbc.url");
            String login = props.getProperty("jdbc.login");
            String password = props.getProperty("jdbc.password");
            con = DriverManager.getConnection(url, login, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }

    public void turnConnection(int size) {
        System.out.println(size + "//////");
        for (int i = 0; i < size; i++) {
            myConnection.add(connectionFactory());
        }
    }

    public void removeConnection(Connection con) {
        System.out.println("le client rend une connexion au serveur");
        myConnection.add(con);
    }

    public Connection addConnection() {
        if (myConnection.size() == 0) {
            try {
                throw new Exception("Aucune connection disponible");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Ajout de connexion");
        Connection con = connectionFactory();
        System.out.println(myConnection.size() + "le nombre de connexion disponible");
        myConnection.remove(myConnection.size() - 1);
        System.out.println(myConnection.size() + "le serveur envoie une connexion au client");
        return con;

    }

    public void closeConnection() {
        for (Connection con : myConnection)
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
