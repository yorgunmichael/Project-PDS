package episen.backend.prototype;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import episen.backend.pool.DataSource;
import episen.backend.pool.PropertiesClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ClientThread extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(BackendService.class.getName());
    private static DataSource dataSource;


    @Override
    public void run() {
        try {
            logger.info("débute run");
            crud();
        } catch (InterruptedException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void crud() throws InterruptedException, JsonProcessingException {
        logger.info("débute crud");


        Map<String, String> map = new HashMap<>();
        map.put("nom", "Chacha");
        map.put("prenom", "Michael");
        map.put("age", "25");


        dataSource = DataSource.getInstance();
        Connection co = dataSource.giveConnection();
        logger.info(crud_read(co, map).toString());
        sleep(10000);
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


}
