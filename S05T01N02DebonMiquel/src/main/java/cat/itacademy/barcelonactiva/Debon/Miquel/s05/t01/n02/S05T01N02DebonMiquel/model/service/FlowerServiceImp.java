package cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.service;

import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO.FlowerDTO;
import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO.FlowerDTOReturn;
import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.domainEntity.Flower;
import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.repository.FlowerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlowerServiceImp implements IFlowerService{


    @Autowired
    private FlowerRepository repository;
    static Logger LOG = LoggerFactory.getLogger(FlowerServiceImp.class);

    @Override
    public boolean existFlowerById(int id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<FlowerDTO> getOne(int id){
        Optional<Flower> optional = repository.findById(id);
        if(optional.isPresent()){
            FlowerDTO dto = DTOfromFlower(optional.get());
            LOG.info(String.format("Service - Flower '%d' EXIST",id));
            return Optional.of(dto);
        }else{
            LOG.warn(String.format("Service - Flower '%d' DO NOT exist",id));
            return Optional.empty();
        }
    }

    @Override
    public List<FlowerDTO> getAll() {
        if(repository.findAll().size()>0){
            LOG.warn(String.format("Service - There are Flowers"));
            return repository.findAll().stream()
                    .map(x -> DTOfromFlower(x))
                    .collect(Collectors.toList());
        }else{
            LOG.warn(String.format("Service - There are NO Flowers"));
            return new ArrayList<>();
        }
    }

    @Override
    public FlowerDTOReturn delete(int id) {
        FlowerDTOReturn dtoReturn = null;
        if(repository.existsById(id)){
            LOG.info(String.format("Service - Flower '%d' EXIST to Delete",id));
            dtoReturn = DTOReturnFromFlower(repository.findById(id).get());
            repository.deleteById(id);
            return dtoReturn;
        }else{
            LOG.warn(String.format("Service - Flower '%d' DO NOT exist",id));
            return dtoReturn;
        }
    }

    @Override
    public FlowerDTOReturn save(FlowerDTO dto)  throws RuntimeException {
        FlowerDTO flowerDTO = new FlowerDTO(dto.getName(), dto.getCountry());
        Flower flower = flowerFromDTOSave(dto);
        repository.save(flower);
        return new FlowerDTOReturn(flowerDTO.getName(), flowerDTO.getCountry(), flowerDTO.getEurope());
    }

    @Override
    public FlowerDTOReturn update(FlowerDTO dto) {
        Flower flower = flowerFromDTOUpdate(dto);
        repository.save(flower);
        return DTOReturnFromFlower(flower);
    }



    /**
     *
     * @param flower
     * @return DTO
     */

    private FlowerDTOReturn DTOReturnFromFlower(Flower flower){
        FlowerDTO dto = DTOfromFlower(flower);
        return new FlowerDTOReturn(dto.getName(), dto.getCountry(), dto.getEurope());
    }
    private FlowerDTO DTOfromFlower(Flower flower){
        return new FlowerDTO(flower.getPk_FlorID() ,flower.getNomFlor(), flower.getPaisFlor());
    }
    private Flower flowerFromDTOSave(FlowerDTO dto){
        return new Flower(dto.getName(), dto.getCountry());
    }
    private Flower flowerFromDTOUpdate(FlowerDTO dto){
        return new Flower(dto.getId(), dto.getName(), dto.getCountry());
    }

}
