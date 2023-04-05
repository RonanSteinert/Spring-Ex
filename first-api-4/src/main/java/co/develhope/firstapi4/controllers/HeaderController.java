package co.develhope.firstapi4.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/headers")
public class HeaderController {
    @GetMapping
    public String getHostInfo(@RequestHeader("host") String host) {
        String[] hostInfo = host.split(":");
        return "Host name: " + hostInfo[0] + "Host port: " + hostInfo[1];
    }

}