package episen.backend.prototype;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

import static java.lang.Thread.sleep;


public class BackendService {

    private static final Logger logger = LoggerFactory.getLogger(BackendService.class.getName());


    public static void main(String[] args) throws IOException {

        try {
            final Options options = new Options();
            final Option mode = Option.builder().longOpt("mode").hasArg().argName("mode").build();
            options.addOption(mode);
            final CommandLineParser commandLineParser = new DefaultParser();
            final CommandLine commandLine = commandLineParser.parse(options, args);
            if (commandLine.hasOption("mode")) {
                String requestmode = commandLine.getOptionValue("mode");
                logger.info(requestmode);
                if (requestmode.contains("json")) {
                    ServerSocket serverSocket = new ServerSocket(5039);
                    serverSocket.setSoTimeout(60000);
                    Socket socket;
                    while (true) {
                        socket = serverSocket.accept();
                        OutputStream out = socket.getOutputStream();
                        InputStream in = socket.getInputStream();
                        DataOutputStream ds = new DataOutputStream(out);
                        DataInputStream di = new DataInputStream(in);
                        sleep(2000);
                        String request = di.readUTF();
                        System.out.println(request);
                        ObjectMapper mapper = new ObjectMapper(new JsonFactory());

                        Map<String, String> map = mapper.readValue(request.split("@")[1], new TypeReference<Map<String, String>>() {
                        });
                        if (request.split("@")[0].equals("Etudiant1")) {
                            ds.writeUTF("Bonjour "+map.get("nom")+" "+map.get("prenom")+" vous avez "+map.get("age")+" ans");
                        }
                    }

                }
            }
        } catch (ParseException | InterruptedException e) {
            e.printStackTrace();
        }

        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("/usr/local/newera/resources/conf.properties")) {
            props.load(fis);
        }
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        PropertiesClass yamlProps = mapper.readValue(new File("/usr/local/newera/resources/config.yaml"), PropertiesClass.class);
        int maxCo = yamlProps.getMaxCo();
        DataSource ds = new DataSource(maxCo, props);

    }

}

