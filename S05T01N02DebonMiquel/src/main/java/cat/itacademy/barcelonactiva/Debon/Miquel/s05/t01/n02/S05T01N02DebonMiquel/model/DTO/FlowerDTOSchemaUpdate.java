package cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperties;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Schema(hidden = true)
public class FlowerSchemaUpdate {

    //This class is use as the Scheme for the UPDATE method

    @Id
    @Schema
    private int id;
    @Schema(defaultValue = "Rosa")
    private String name;
    @Schema(defaultValue = "Spain")
    private String country;

}
