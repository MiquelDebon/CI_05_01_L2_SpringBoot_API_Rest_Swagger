package cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.service;

import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO.FlowerDTO;
import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO.FlowerDTOReturn;
import java.util.List;
import java.util.Optional;

public interface IFlowerService {
    boolean existFlowerById(int id);
    Optional<FlowerDTO> getOne(int id);
    List<FlowerDTO> getAll();
    void delete(int id);
    FlowerDTOReturn save(FlowerDTO dto);
    void update(FlowerDTO dto);

}
