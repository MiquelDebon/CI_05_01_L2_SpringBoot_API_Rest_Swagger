package cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel;

import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.controller.FlowerController;
import cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel.model.DTO.FlowerDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Test Titulo")
public class TestJunit {

    /**
     * tos els metodes
     *  add -> amb jason amb i sense id o co
     *
     *
     *
     */

    @Test
    @DisplayName("Test 1")
    public void test1(){
        FlowerController flowerController = new FlowerController();

        flowerController.add(new FlowerDto("mimim", "dexec"));
//        assertEquals(ResponseEntity<>(HttpStatus.OK));

    }
}
