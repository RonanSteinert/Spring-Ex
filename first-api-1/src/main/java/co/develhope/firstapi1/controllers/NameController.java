package co.develhope.firstapi1.controllers;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

@RestController
public class NameController {
    @GetMapping(value = "/echo/{name}")
    public String getName(@PathVariable (value = "name") String name){
        return "il nome è " + name;
    }
    @PostMapping(value = "/echo/{name}")
    public String postname(@PathVariable(value = "name") String name){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        return stringBuilder.reverse().toString();
    }

}