package cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class FlowerDTO {
    private int id;
    private String name;
    private String country;
    private String Europe;

    public FlowerDTO(int id, String name, String country){
        this.id = id;
        this.name = name;
        this.country = country;
        this.Europe = UECountries.UEcountriesList.stream()
                .map(String::toLowerCase)
                .anyMatch(country.toLowerCase()::contains) ? "UE" : "Fora UE";
    }
}
