package cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FlowerDtoReturn {
    @Schema(defaultValue = "Rose", description = "Flower's Name")
    private String name;
    @Schema(defaultValue = "Spain", description = "Flower's Country")
    private String country;
    @Schema(defaultValue = "EU / No EU", description = "Flower is from Europe?")
    private String europe;

    public FlowerDtoReturn(String name, String country, String europe){
        this.name = name;
        this.country = country;
        this.europe = europe;
    }
}
