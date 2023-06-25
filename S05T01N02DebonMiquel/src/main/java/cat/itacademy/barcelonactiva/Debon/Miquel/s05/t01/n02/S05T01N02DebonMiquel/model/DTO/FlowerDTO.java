package cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FlowerDTO {
    @Id
    @Schema(hidden = true)
    private int id;

    @NotEmpty(message = "Name is mandatory")
    @Schema(defaultValue = "MiquelFlower", description = "Name of the Flower")
    private String name;
    @NotEmpty(message = "Country is mandatory")
    @Schema(defaultValue = "Spain")
    private String country;
    @Schema()
    private String Europe;

    public FlowerDTO( String name, String country){
        this.name = name;
        this.country = country;
        this.Europe = UECountries.UEcountriesList.stream()
                .map(String::toLowerCase)
                .anyMatch(country.toLowerCase()::contains) ? "UE" : "Fora UE";
    }
    public FlowerDTO(int id, String name, String country){
        this.id = id;
        this.name = name;
        this.country = country;
        this.Europe = UECountries.UEcountriesList.stream()
                .map(String::toLowerCase)
                .anyMatch(country.toLowerCase()::contains) ? "UE" : "Fora UE";
    }
}
