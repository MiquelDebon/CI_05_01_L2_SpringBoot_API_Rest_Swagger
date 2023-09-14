package cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.service;

import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO.FlowerDto;
import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO.FlowerDtoRequest;
import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO.FlowerDtoReturn;
import java.util.List;
import java.util.Optional;

public interface IFlowerService {
    boolean existFlowerById(int id);
    Optional<FlowerDto> getOne(int id);
    List<FlowerDto> getAll();
    FlowerDtoReturn delete(int id);
    FlowerDtoReturn save(FlowerDtoRequest dto);
    FlowerDtoReturn update(FlowerDto dto);

}
