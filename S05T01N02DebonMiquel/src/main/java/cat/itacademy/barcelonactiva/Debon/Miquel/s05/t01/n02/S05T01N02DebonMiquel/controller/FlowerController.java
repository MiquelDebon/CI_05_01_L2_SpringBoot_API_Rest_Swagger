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
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/flower")
public class FlowerController {
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
            FlowerDTOReturn dtoReturn = flowerService.update(dto);
            return new ResponseEntity<>(dtoReturn, HttpStatus.OK);
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
        LOG.info("Controller - Deleting method running");

        FlowerDTOReturn dtoReturn = flowerService.delete(id);

        if(dtoReturn != null){
            return new ResponseEntity<>(dtoReturn,HttpStatus.OK);
        }else{
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
                    ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No right Id: '" + id +"'")); //HTTP Body Message
            return ResponseEntity.ok().body(dto);
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
        List<FlowerDTO> dtoList = flowerService.getAll();
        if(dtoList.size()>0){
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
        }else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Header - No content", "There is no values yet");
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }




}
