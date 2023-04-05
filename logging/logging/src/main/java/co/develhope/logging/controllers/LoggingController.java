package co.develhope.logging.controllers;

import co.develhope.logging.services.LoggingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {

    @Autowired
    private LoggingService loggingService;

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @GetMapping("/")
    public String welcome(){
        logger.info("This is a welcome message");
        return "Welcome";
    }

    @GetMapping("/exp")
    public Integer doExp(){
        return loggingService.doPower();
    }

    @GetMapping("/get-errors")
    public void errors(){
        Error error = new Error("This is the error");
        logger.error("This is an ERROR logging", error);
    }
}
