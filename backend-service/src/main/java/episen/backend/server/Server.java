package episen.backend.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.Properties;

public class Server {

    private static final Logger logger = LoggerFactory.getLogger(Server.class.getName());
    private ServerSocket server;
    private DataSource ds;

    public Server(ServerConfiguration c) {

        try {
            this.server = new ServerSocket(c.getConfiguration().getPort());
            Properties props = new Properties();
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties"));
            this.ds = new DataSource(c.getN(), props);
            logger.debug("Server starting...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serverService() {
        try {
            while (true) {
                // socket object to receive incoming client
                Socket client = server.accept();
                logger.debug("A new client is here !");

                //create a new thread object
                Connection connection = ds.addData();
//                ClientHandler clientSocket = new ClientHandler(client, connection);
//
//                // This thread will handle the client
//                new Thread(clientSocket).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Server(new ServerConfiguration(25)).serverService();
    }
}