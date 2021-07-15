package episen.backend.server;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class BackendService {

    private static final Logger logger = LoggerFactory.getLogger(BackendService.class.getName());

    public static void main(String[] args) {
        logger.info("BackendService is running");

        final Options options = new Options();
        final Option testMode = Option.builder().longOpt("testMod").build();
        options.addOption(testMode);

    }
}
