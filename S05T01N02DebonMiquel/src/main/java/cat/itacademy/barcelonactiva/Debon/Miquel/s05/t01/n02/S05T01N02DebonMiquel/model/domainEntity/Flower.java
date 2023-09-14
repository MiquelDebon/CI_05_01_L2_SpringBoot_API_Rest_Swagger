package cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.domainEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flower {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true)
    private int pk_FlorID;

    @Column(name="name")
    private String nomFlor;

    @Column(name="country")
    private String paisFlor;

    public Flower (String name, String country){
        this.nomFlor = name;
        this.paisFlor = country;
    }
}
