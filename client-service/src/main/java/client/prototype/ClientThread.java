package client.prototype;

import com.fasterxml.jackson.core.JsonProcessingException;
import episen.backend.prototype.BackendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static client.prototype.ClientPrototype.getSendd;

public class ClientThread extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(BackendService.class.getName());


    @Override
    public void run() {
        getSendd("Etudiant1", "read");


    }




}
