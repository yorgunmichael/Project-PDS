package episen.backend.prototype;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import episen.backend.pool.DataSource;
import episen.backend.pool.PropertiesClass;
import episen.backend.server.Server;
import episen.backend.server.ServerConfiguration;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import static java.lang.Thread.sleep;


public class BackendService {
    private static final Logger logger = LoggerFactory.getLogger(BackendService.class.getName());
    private static DataSource dataSource;

    public BackendService() {
    }

    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("/usr/local/newera/resources/conf.properties")) {
            props.load(fis);
        }
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        PropertiesClass yamlProps = mapper.readValue(new File("/usr/local/newera/resources/config.yaml"), PropertiesClass.class);
        int maxCo = yamlProps.getMaxCo();
        dataSource = new DataSource(maxCo, props);
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
                    crud();
                } else if (requestmode.contains("overload")) {
                    pooloverload(3,props);
                }

            }

        } catch (ParseException | SQLException | InterruptedException e) {
            e.printStackTrace();
        }


    }

    private static void pooloverload(int nbCo, Properties props) throws InterruptedException {
        dataSource = new DataSource(nbCo,props);
        while (true) {
            sleep(2000);
            dataSource.getConnection();
        }
    }


    private static void crud() throws IOException, SQLException {
        ServerSocket serverSocket = new ServerSocket(5039);
        serverSocket.setSoTimeout(60000);
        Socket socket;
        socket = serverSocket.accept();


        System.out.println("j'entre dans la méthode");
        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();
        DataOutputStream ds = new DataOutputStream(out);
        DataInputStream di = new DataInputStream(in);

        System.out.println("Encore toi");
        while (true) {
            String crud_op = di.readUTF();
            System.out.println(crud_op);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String request = di.readUTF();
            System.out.println(request);
            ObjectMapper mapper = new ObjectMapper(new JsonFactory());

            Map<String, String> map = mapper.readValue(request.split("@")[1], new TypeReference<Map<String, String>>() {
            });
            String req = request.split("@")[0];
            Connection co = dataSource.getConnection();
            if (req.contains("Etudiant") & crud_op.contains("insert")) {
                ds.writeUTF(crud_insert(co, map).toString());
                System.out.println("fin de crud");
            } else if (req.contains("Etudiant") & crud_op.equals("delete")) {
                ds.writeUTF(crud_delete(co, map).toString());
            }else if (req.contains("Etudiant") & crud_op.equals("update")) {
                ds.writeUTF(crud_update(co, map).toString());
            }
            else if (req.contains("Etudiant") & crud_op.equals("read")) {
                ds.writeUTF(crud_read(co, map).toString());
            }
            dataSource.addConnection(co);
            co.close();
        }

    }

    private static Object crud_read(Connection connection, Map<String, String> map) {
        return null;
    }

    private static StringBuilder crud_update(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;

        try {
            String sql = "Update student set old = '"+ map.get("age")+ "'where name ='" + map.get("nom")+"'" ;
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
                ds.writeUTF("Bonjour " + map.get("nom1") + " " + map.get("prenom1") + " vous avez " + map.get("age1") + " ans");
            }
            if (request.split("@")[0].equals("Etudiant2")) {
                ds.writeUTF("Bonjour " + map.get("nom2") + " " + map.get("prenom2") + " vous avez " + map.get("age2") + " ans");
            }
            if (request.split("@")[0].equals("Etudiant3")) {
                ds.writeUTF("Bonjour " + map.get("nom3") + " " + map.get("prenom3") + " vous avez " + map.get("age3") + " ans");
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




