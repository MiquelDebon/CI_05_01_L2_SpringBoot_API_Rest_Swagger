package cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.controller;

import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO.FlowerDTO;
import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.service.FlowerServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/flower")
public class FlowerController {

    @Autowired
    private  FlowerServiceImp flowerService;

    //http://localhost:9001/v3/api-docs
    //http://localhost:9001/swagger-ui/index.html

    @GetMapping("/getOne/{id}")
    @Operation(
            tags = "hello"
    )
    public ResponseEntity<FlowerDTO> getOne(@PathVariable int id){

        try{
            Optional<FlowerDTO> optional = flowerService.getOne(id);
            FlowerDTO dto = optional.get();
            return new ResponseEntity<FlowerDTO>(dto,HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getStatusCode());
        }

    }

}
