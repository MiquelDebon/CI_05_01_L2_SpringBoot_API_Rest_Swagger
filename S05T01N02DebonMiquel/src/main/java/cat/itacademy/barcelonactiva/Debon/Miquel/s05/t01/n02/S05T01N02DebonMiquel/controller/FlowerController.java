package cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.controller;

import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO.FlowerDTO;
import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.domainEntity.Flower;
import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.service.FlowerServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/flower")
public class FlowerController {

    @Autowired
    private  FlowerServiceImp flowerService;

    //http://localhost:9001/v3/api-docs
    //http://localhost:9001/swagger-ui/index.html

    static Logger LOG = LoggerFactory.getLogger(FlowerServiceImp.class);

    @GetMapping("/getOne/{id}")
    @Operation(
            tags = "IT-Academy",
            summary = "Get one Flower DTO",
            description = "Description: Use the repository to extract the Flower entity " +
                    "and then became it to DTO"
    )
    public ResponseEntity<FlowerDTO> getOne(@PathVariable int id){
            Optional<FlowerDTO> optional = flowerService.getOne(id);
            FlowerDTO dto = optional.orElseThrow(
                    ()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
            return new ResponseEntity<FlowerDTO>(dto,HttpStatus.OK);

//        try{
//            Optional<FlowerDTO> optional = flowerService.getOne(id);
//            FlowerDTO dto = optional.get();
//            return new ResponseEntity<FlowerDTO>(dto,HttpStatus.OK);
//        }catch (ResponseStatusException rse){
//            return new ResponseEntity<>(rse.getStatusCode());
//        }
    }

    @GetMapping("/getAll")
    @Operation(
            tags = "IT-Academy",
            summary = "Get ALL Flower DTO"
    )
    public ResponseEntity<List<FlowerDTO>> getAll(){
        try{
            List<FlowerDTO> dtoList = flowerService.getAll();
            return new ResponseEntity<List<FlowerDTO>>(dtoList, HttpStatus.OK) ;
        }catch (ResponseStatusException rse){
            return new ResponseEntity<>(rse.getStatusCode());
        }
    }

    @PutMapping("/save")
    @Operation(
            tags = "IT-Academy",
            summary = "SAVE one flower",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(schema = @Schema(implementation = Flower.class))
            )
    )
    public ResponseEntity<?> save(@Valid @RequestBody Flower flower){
        LOG.info("Saving method");
        try{
            flowerService.save(flower);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
