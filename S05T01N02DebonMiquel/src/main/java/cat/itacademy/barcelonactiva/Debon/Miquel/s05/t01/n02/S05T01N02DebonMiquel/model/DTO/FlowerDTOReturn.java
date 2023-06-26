package cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

public class FlowerReturn {

    @NotEmpty(message = "Name is mandatory")
    @Schema(defaultValue = "Rose", description = "Flower's Name")
    private String name;
    @NotEmpty(message = "Country is mandatory")
    @Schema(defaultValue = "Spain", description = "Flower's Country")
    private String country;

    @Schema(hidden = true)
    private String europe;

    public FlowerDTO( String name, String country){
        this.name = name;
        this.country = country;
        this.europe = UECountries.UEcountriesList.stream()
                .map(String::toLowerCase)
                .anyMatch(country.toLowerCase()::contains) ? "UE" : "Fora UE";
    }
}
