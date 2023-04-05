package co.develhope.Swagger01.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/name")
public class NameController {
    @Operation(summary = "Get name", description = "This controllers returns the name")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(responseCode = "400",description = "BAD DATA"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR"),
            @ApiResponse(responseCode = "200",description = "CREATED")})
    @GetMapping("/name")
    public String getName(@RequestParam String name){
        return name;
    }
}
