package cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.domainEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor // IMPORTANT!!
public class Flower {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name="id", unique = true)
    private int pk_FlorID;

    @Column(name="name")
    private String nomFlor;

    @Column(name="country")
    private String paisFlor;

}
