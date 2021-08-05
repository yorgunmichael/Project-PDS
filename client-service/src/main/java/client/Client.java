package client;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import static java.lang.Thread.sleep;




public class Client {
    private final static Logger log = LoggerFactory.getLogger(Client.class.getName());
    private final static String configurationvariable = "Configurations";
    private static String configuration;
    private ClientProperties propconfig;
    public static Map<String, Map<String, String>> map;

    public static void main(String[] args) {

        try {

            configuration = System.getenv(configurationvariable);
            String values = Files.readString(Path.of(configuration));
          //  String values = Files.readString(Path.of(configuration));
            System.out.println(values);


            ObjectMapper jmapper = new ObjectMapper(new JsonFactory());

           map = jmapper.readValue(values, new TypeReference<Map<String, Map<String, String>>>() {});
            sleep(1000);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
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
            // System.out.println(request);
            //System.out.println(data);
            outputData.writeUTF(request + "@" + data);
            answer = inputData.readUTF();
            // System.out.println(answer);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
}
