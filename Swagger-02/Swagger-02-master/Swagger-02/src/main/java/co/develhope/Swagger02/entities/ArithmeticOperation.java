package co.develhope.Swagger02.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArithmeticOperation {

    @Schema(name = "name",description = "The name of the arithmetic operation")
    private String name;
    @Schema(name = "minNumberOfOperands",description = "The minimum number of operands of the arithmetic operation")
    private Integer minNumberOfOperands;
    @Schema(name = "description",description = "The description of the arithmetic operation")
    private String description;
    @Schema(name = "properties",description = "The properties of the arithmetic operation")
    private String[] properties;
}
