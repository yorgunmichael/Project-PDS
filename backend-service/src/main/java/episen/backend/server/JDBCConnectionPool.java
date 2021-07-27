package episen.backend.server;

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
            con = DriverManager.getConnection(url,login, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }

    public void turnConnection(int size) {
        for(int i= 0; i < size; i++) {
            myConnection.add(connectionFactory());
        }
    }

    public void removeConnection(Connection con) {
        synchronized (myConnection) {
            while (true) {
                if(!myConnection.isEmpty()) {
                    myConnection.remove(con);
                    return;
                }
                else
                    try{
                        myConnection.wait(3000);
                        addConnection();
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    public Connection addConnection() {
        Connection con = connectionFactory();
        myConnection.add(con);
        return con;
    }

    public void closeConnection() {
        for(Connection con: myConnection)
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
