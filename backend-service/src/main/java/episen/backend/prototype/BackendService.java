package episen.backend.prototype;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import episen.backend.pool.DataSource;
import episen.backend.server.ClientHandlerPrototype;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static java.lang.Thread.sleep;


public class BackendService {
    private static final Logger logger = LoggerFactory.getLogger(BackendService.class.getName());
    private static DataSource dataSource;
    private static ServerSocket serverSocket;
    private static Socket socket;


    public BackendService() {
    }

    public static void main(String[] args) throws IOException {
        dataSource = DataSource.getInstance();

        try {
            final Options options = new Options();
            final Option mode = Option.builder().longOpt("mode").hasArg().argName("mode").build();
            final Option modecrud = Option.builder().longOpt("modecrud").argName("modecrud").build();
            options.addOption(mode);
            options.addOption(modecrud);

            final CommandLineParser commandLineParser = new DefaultParser();
            final CommandLine commandLine = commandLineParser.parse(options, args);

            if (commandLine.hasOption("mode")) {
                String requestmode = commandLine.getOptionValue("mode");
                logger.info(requestmode);
                if (requestmode.contains("json")) {
                    receiveJson();
                } else if (requestmode.contains("crud")) {
                    startCrud(false, dataSource.giveConnection());
                } else if (requestmode.contains("overload")) {
                    while (true) {

                        serverSocket = new ServerSocket(5039);
                        serverSocket.setSoTimeout(60000);
                        socket = serverSocket.accept();
                        new Thread(new ClientHandlerPrototype()).start();
                    }
                }

            }

        } catch (ParseException | SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void startCrud(boolean overload, Connection co) throws IOException, SQLException, InterruptedException {

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            DataOutputStream ds = new DataOutputStream(out);
            DataInputStream di = new DataInputStream(in);
            String crud_op = di.readUTF();
            logger.info(crud_op);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String request = di.readUTF();
            logger.info(request);
            ObjectMapper mapper = new ObjectMapper(new JsonFactory());

            Map<String, String> map = mapper.readValue(request.split("@")[1], new TypeReference<Map<String, String>>() {
            });
            String req = request.split("@")[0];
            co = dataSource.giveConnection();
            if (req.contains("Etudiant") & crud_op.contains("insert")) {
                ds.writeUTF(crud_insert(co, map).toString());
                logger.info("Exécution insert");
            } else if (req.contains("Etudiant") & crud_op.equals("delete")) {
                ds.writeUTF(crud_delete(co, map).toString());
            } else if (req.contains("Etudiant") & crud_op.equals("update")) {
                ds.writeUTF(crud_update(co, map).toString());
            } else if (req.contains("Etudiant") & crud_op.equals("read")) {
                ds.writeUTF(crud_read(co, map).toString());
            }
            if (overload) {

            }
            dataSource.retrieveConnection(co);

        }



    private static StringBuilder crud_read(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;

        try {
            String sql = "select s.name, first_name, old\n" +
                    "from student s\n" +
                    "Where s.name = '" + map.get("nom") + "'";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);

            sb = new StringBuilder();
            while (rs.next()) {
                sb.append("nom: " + rs.getString(1) + " ");
                sb.append("prenom: " + rs.getString(2) + " ");
                sb.append("age: " + rs.getString(3) + " ans");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    private static StringBuilder crud_update(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;

        try {
            String sql = "Update student set old = '" + map.get("age") + "'where name ='" + map.get("nom") + "'";
            connection.createStatement().executeUpdate(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            sb.append("Update done.");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    private static void receiveJson() throws IOException {
        ServerSocket serverSocket = new ServerSocket(5039);
        serverSocket.setSoTimeout(60000);
        Socket socket;
        while (true) {
            socket = serverSocket.accept();
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            DataOutputStream ds = new DataOutputStream(out);
            DataInputStream di = new DataInputStream(in);
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String request = di.readUTF();
            logger.info(request);
            ObjectMapper mapper = new ObjectMapper(new JsonFactory());

            Map<String, String> map = mapper.readValue(request.split("@")[1], new TypeReference<Map<String, String>>() {
            });
            if (request.split("@")[0].equals("Etudiant1")) {
                ds.writeUTF("Bonjour " + map.get("nom") + " " + map.get("prenom") + " vous avez " + map.get("age") + " ans");
            }
            if (request.split("@")[0].equals("Etudiant2")) {
                ds.writeUTF("Bonjour " + map.get("nom") + " " + map.get("prenom") + " vous avez " + map.get("age") + " ans");
            }
            if (request.split("@")[0].equals("Etudiant3")) {
                ds.writeUTF("Bonjour " + map.get("nom") + " " + map.get("prenom") + " vous avez " + map.get("age") + " ans");
            }
            if (request.split("@")[0].equals("Etudiant4")) {
                ds.writeUTF("Bonjour " + map.get("nom") + " " + map.get("prenom") + " vous avez " + map.get("age") + " ans");
            }
            if (request.split("@")[0].equals("Etudiant5")) {
                ds.writeUTF("Bonjour " + map.get("nom") + " " + map.get("prenom") + " vous avez " + map.get("age") + " ans");
            }
            if (request.split("@")[0].equals("Etudiant6")) {
                ds.writeUTF("Bonjour " + map.get("nom") + " " + map.get("prenom") + " vous avez " + map.get("age") + " ans");
            }
            if (request.split("@")[0].equals("Etudiant7")) {
                ds.writeUTF("Bonjour " + map.get("nom") + " " + map.get("prenom") + " vous avez " + map.get("age") + " ans");
            }
            if (request.split("@")[0].equals("Etudiant22")) {
                ds.writeUTF("Bonjour " + map.get("nom") + " " + map.get("prenom") + " vous avez " + map.get("age") + " ans");
            }
        }
    }


    public static StringBuilder crud_insert(Connection connection, Map<String, String> map) throws IOException {
        StringBuilder sb = null;

        try {
            String sql = "INSERT INTO student (name, first_name, old)" +
                    "values ('" + map.get("nom") + "','" + map.get("prenom") + "','" + map.get("age") + "')";
            connection.createStatement().executeUpdate(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            sb.append("Insert done.");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;

    }

    public static String crud_delete(Connection connection, Map<String, String> map) {
        String sb = null;

        try {
            String sql = "DELETE from student " +
                    "where student.name ='" + map.get("nom") + "'";
            connection.createStatement().executeUpdate(sql);
            System.out.println(sql);
            sb = "Delete done.";

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }
}




