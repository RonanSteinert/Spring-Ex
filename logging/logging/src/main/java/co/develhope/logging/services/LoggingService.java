package co.develhope.logging.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {

    @Autowired
    private Environment environment;

    Logger logger = LoggerFactory.getLogger(LoggingService.class);

    public Integer doPower(){
        try{
            logger.debug("Calculating the power");
            return (int) Math.pow(Integer.parseInt(environment.getProperty("var1")),Integer.parseInt(environment.getProperty("var2")));
        } finally {
            logger.debug("Power calculated");
        }
    }
}
