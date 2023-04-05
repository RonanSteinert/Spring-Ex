package co.develhope.interceptormiddleware02.controllers;

import co.develhope.interceptormiddleware02.entities.Month;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/month")
public class MonthController {

    @GetMapping("")
    public Month getMonth(HttpServletRequest request){
        return (Month) request.getAttribute("monthNumber");
    }
}
