package client.prototype;

import MenuInterface.Home;
import client.ClientConfiguration;
import client.ClientProperties;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static java.lang.Thread.sleep;


public class ClientPrototype {
    private final static Logger log = LoggerFactory.getLogger(ClientPrototype.class.getName());
    private final static String configurationvariable = "Prototype";
    private static String configuration;
    public static Map<String, Map<String, String>> map;

    public static void main(String[] args) {

        try {
            final Options options = new Options();
            final Option student = Option.builder().longOpt("student").hasArg().argName("student").build();
            options.addOption(student);
            final CommandLineParser commandLineParser = new DefaultParser();
            final CommandLine commandLine = commandLineParser.parse(options, args);

            if (commandLine.hasOption("student")) {
                String request = commandLine.getOptionValue("student");
                configuration = System.getenv(configurationvariable);
                String values = Files.readString(Path.of(configuration));
                ObjectMapper jmapper = new ObjectMapper(new JsonFactory());

                map = jmapper.readValue(values, new TypeReference<Map<String, Map<String, String>>>() {
                });
                getSend(request);
            } else {
                log.info("missing student argument");
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static String getSend(String request) {
        String answer = null;
        try {
            Socket socket = new Socket(new ClientConfiguration().getConfiguration().getAdressIP(), new ClientConfiguration().getConfiguration().getPort());
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            String data = objectMapper.writeValueAsString(map.get(request));
            DataInputStream inputData = new DataInputStream(in);
            DataOutputStream outputData = new DataOutputStream(out);
            log.info(request);
            log.info(data);
            outputData.writeUTF(request + "@" + data);
            answer = inputData.readUTF();
            log.info(answer);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
}
