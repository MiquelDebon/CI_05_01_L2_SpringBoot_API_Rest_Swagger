package cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.controller;

import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO.FlowerDTO;
import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO.FlowerDTOReturn;
import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO.FlowerDTOSchemaUpdate;
import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.service.IFlowerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Flow;

@Controller
@RequestMapping("/flower")
public class FlowerController {

    //TODO Entity Pattern!

    @Autowired
    private IFlowerService flowerService;

    //http://localhost:9001/v3/api-docs
    //http://localhost:9001/swagger-ui/index.html

    static Logger LOG = LoggerFactory.getLogger(FlowerController.class);

    @PostMapping("/add")
    @Operation(
            tags = "IT-Academy",
            summary = "SAVE one flower",
            description = "Description: This method save a new flower in the database",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Expected a Flower JSON",
                    content = @Content(schema = @Schema(implementation = FlowerDTO.class))),

            //TODO: NO estoy exponiendo usuarios demasiado?
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful response",
                            content = @Content(schema = @Schema(implementation = FlowerDTOReturn.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request buddy",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<?> add(@Valid @RequestBody FlowerDTO dto){
        LOG.info("Controller - Saving method");
        try{
            FlowerDTOReturn dtoReturn = flowerService.save(dto);
            return new ResponseEntity<>(dtoReturn, HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>("Not valid Flower",HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/update")
    @Operation(
            tags = "IT-Academy",
            summary = "Update one existing flower",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Expected a Flower JSON",
                    content = @Content(schema = @Schema(implementation = FlowerDTOSchemaUpdate.class))
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful updated",
                            content = @Content(schema = @Schema(implementation = FlowerDTOReturn.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)

                    ),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content
                    )
            }

    )
    public ResponseEntity<?> update(@RequestBody FlowerDTO dto){
        LOG.info("Controller - Updating method");
        try{
            flowerService.update(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Not valid Flower",HttpStatus.BAD_REQUEST);
        }

    }


    @DeleteMapping("/delete/{id}")
    @Operation(
            tags = "IT-Academy",
            summary = "Delete one object by NºId",
            description = "This method delete the object from the database",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful updated",
                            content = @Content(schema = @Schema(implementation = FlowerDTOReturn.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)

                    ),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<?> delete(@PathVariable int id){
        LOG.info("Controller  Deleting method running");
        try{
            Optional<FlowerDTO> optionaldto = flowerService.getOne(id);
            FlowerDTOReturn flowerDTOReturn = null;
            if(optionaldto.isPresent()){
                FlowerDTO dto = optionaldto.get();
                flowerDTOReturn = new FlowerDTOReturn(dto.getName(), dto.getCountry(),dto.getEurope());
            }
            flowerService.delete(id);
            return new ResponseEntity<>(flowerDTOReturn,HttpStatus.OK);
        }catch(RuntimeException rse){
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/getOne/{id}")
    @Operation(
            tags = "IT-Academy",
            summary = "Get one Flower DTO",
            description = "Description: Use the repository to extract the Flower entity " +
                    "and then became it to DTO",
            parameters = @Parameter(name = "id", required = true, example = "1"),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful updated",
                            content = @Content(schema = @Schema(implementation = FlowerDTOReturn.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)

                    ),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<?> getOne(@PathVariable int id){
            Optional<FlowerDTO> optional = flowerService.getOne(id);
            FlowerDTO dto = optional.orElseThrow(
                    ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No right Id: '" + id +"'"));
            return ResponseEntity.ok().body(dto);
//            return new ResponseEntity<FlowerDTO>(dto,HttpStatus.OK);

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
            summary = "Get ALL Flower DTO",
            description = "This method retrieve all the Flower Collection",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful updated",
                            content = @Content(schema = @Schema(implementation = FlowerDTOReturn.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)

                    ),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<List<FlowerDTO>> getAll(){
        try{
            List<FlowerDTO> dtoList = flowerService.getAll();
            return new ResponseEntity<List<FlowerDTO>>(dtoList, HttpStatus.OK) ;
        }catch (RuntimeException rse){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
