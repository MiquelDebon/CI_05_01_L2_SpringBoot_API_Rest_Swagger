package cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.service;

import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO.FlowerDto;
import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO.FlowerDtoReturn;
import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO.FlowerDtoRequest;
import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.domainEntity.Flower;
import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.repository.FlowerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
    public Optional<FlowerDto> getOne(int id){
        Optional<Flower> optional = repository.findById(id);
        if(optional.isPresent()){
            FlowerDto dto = DTOfromFlower(optional.get());
            LOG.info(String.format("Service - Flower '%d' EXIST",id));
            return Optional.of(dto);
        }else{
            LOG.warn(String.format("Service - Flower '%d' DO NOT exist",id));
            return Optional.empty();
        }
    }

    @Override
    public List<FlowerDto> getAll() {
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
    public FlowerDtoReturn delete(int id) {
        FlowerDtoReturn dtoReturn = null;
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
    public FlowerDtoReturn save(FlowerDtoRequest dto){
        FlowerDto flowerDTO = DTOfromFlowerRequest(dto);
        Flower flower = flowerFromFlowerRequest(dto);
        repository.save(flower);
        return new FlowerDtoReturn(flowerDTO.getName(), flowerDTO.getCountry(), flowerDTO.getEurope());
    }

    @Override
    public FlowerDtoReturn update(FlowerDto dto) {
        if(repository.findById(dto.getId()).isPresent()){
            Flower flower = flowerFromDTOUpdate(dto);
            repository.save(flower);
            return DTOReturnFromFlower(flower);
        }else{
            throw  new NoSuchElementException();
        }
    }



    /**
     *
     * @param flower
     * @return DTO
     */

    private FlowerDtoReturn DTOReturnFromFlower(Flower flower){
        FlowerDto dto = DTOfromFlower(flower);
        return new FlowerDtoReturn(dto.getName(), dto.getCountry(), dto.getEurope());
    }
    private FlowerDto DTOfromFlower(Flower flower){
        return new FlowerDto(flower.getPk_FlorID() ,flower.getNomFlor(), flower.getPaisFlor());
    }
    private Flower flowerFromDTOUpdate(FlowerDto dto){
        return new Flower(dto.getId(), dto.getName(), dto.getCountry());
    }
    private Flower flowerFromFlowerRequest(FlowerDtoRequest flowerRequest){
        return new Flower(flowerRequest.getName(), flowerRequest.getCountry());
    }
    private FlowerDto DTOfromFlowerRequest(FlowerDtoRequest flowerRequest){
        return new FlowerDto(flowerRequest.getName(), flowerRequest.getCountry());
    }

}
