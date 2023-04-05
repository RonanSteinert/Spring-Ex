package co.develhope.deploy02.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sum")
public class SumController {

    @GetMapping("")
    public Integer sum(@RequestParam Integer a, @RequestParam Integer b){
        return a+b;
    }
}
