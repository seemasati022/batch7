package com.tejait.batch7;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Batch7Application {

    public static final Logger logger = LogManager.getLogger(Batch7Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Batch7Application.class, args);
        logger.debug("debug level");
        logger.info("info level");
        logger.warn("warn level");
        logger.error("error level");
        logger.fatal("fatal level");
    }

}
