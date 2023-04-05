package co.develhope.firstapi3.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stringController")
public class StringController {
    @GetMapping
    public String get(@RequestParam(value = "String") String uno,
                      @RequestParam(value = "String", required = false) String due){
        if (due == null){
            return uno;
        }return uno + " " + due;
    }
}