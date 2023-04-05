package co.develhope.Swagger02.controller;

import co.develhope.Swagger02.entities.ArithmeticOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/math")
public class MathController {

    @GetMapping("/")
    @Operation(summary = "Math controller", description = "This controller gives information about math operations")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(responseCode = "400",description = "BAD DATA"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR"),
            @ApiResponse(responseCode = "200",description = "CREATED")})
    public String welcomeMathMsg(){
        return "Welcome math msg";
    }

    @GetMapping("/division-info")
    @Operation(summary = "divison info", description = "This method gives info about the division operation")
        public ArithmeticOperation getArithmeticOperation(){
        return new ArithmeticOperation(
                "division",
                2,
                "This operation divides two numbers (divident and divisor) and returns the quotient", new String[]{
                        "identity","zero","dividing a number for itself"});
    }

    @GetMapping("/multiplication")
    @Operation(summary = "multiplication", description = "This method does the multiplication of two input numbers")
    public int getMultiplicationValue(@Parameter(description = "1st input number") @RequestParam int number1, @Parameter(description = "2nd input number") @RequestParam int number2){
        return number1*number2;
    }

    @GetMapping("/square/{n}")
    @Operation(summary = "square", description = "This method does the square of an input number")
    public double getSquare(@Parameter(description = "Random number n to square")@PathVariable double n){
        return n*n;
    }
}
