package cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.service;

import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO.FlowerDTO;
import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.domainEntity.Flower;
import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.repository.FlowerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public Optional<FlowerDTO> getOne(int id) {
        Optional<Flower> optional = repository.findById(id);
        if(optional.isPresent()){
            FlowerDTO dto = flowerToDTO(optional.get());
            LOG.info(String.format("Flower '%d' EXIST",id));
            return Optional.of(dto);
        }else{
            LOG.warn(String.format("Flower '%d' NO EXIST",id));
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public List<FlowerDTO> getAll() {
        if(repository.findAll().size()>0){
            return repository.findAll().stream()
                    .map(x -> flowerToDTO(x))
                    .collect(Collectors.toList());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
    @Override
    public void save(Flower flower) {
        repository.save(flower);
    }


    /**
     *
     */

    private FlowerDTO flowerToDTO(Flower flower){
        return new FlowerDTO(flower.getPk_FlorID(), flower.getNomFlor(), flower.getPaisFlor());
    }

}
