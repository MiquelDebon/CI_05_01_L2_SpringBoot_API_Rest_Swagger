package cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowerDtoRequest {
    @NotEmpty(message = "Name is mandatory")
    @Schema(defaultValue = "Rose", description = "Flower's Name")
    private String name;

    @NotEmpty(message = "Country is mandatory")
    @Schema(defaultValue = "Spain", description = "Flower's Country")
    private String country;
}
