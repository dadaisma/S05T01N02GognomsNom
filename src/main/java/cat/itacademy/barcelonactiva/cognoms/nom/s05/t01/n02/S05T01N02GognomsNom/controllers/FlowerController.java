package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02GognomsNom.controllers;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02GognomsNom.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02GognomsNom.model.services.FlowerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flower")
public class FlowerController {

    @Autowired
    private FlowerServiceImpl flowerServiceImpl;

    @GetMapping("/getAll")
    public ResponseEntity<List<FlowerDTO>> getAllFlowersNow(){
        return ResponseEntity.ok().body(flowerServiceImpl.getAllFlowers());
    }



    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> getOneFlower(@PathVariable("id") Integer flowerId){
        return ResponseEntity.ok().body(flowerServiceImpl.getFlowerById(flowerId));
    }


    @PostMapping("/add")
    public ResponseEntity<?> addFlower(@RequestBody FlowerDTO flowerDTO){
       flowerServiceImpl.createFlower(flowerDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(flowerDTO);

    }

    @PutMapping( "/update/{id}")
    public ResponseEntity<?>  updateFlowerEdit(@PathVariable("id") Integer id,@RequestBody FlowerDTO flowerDTO)  {

            flowerDTO.setPkFlowerID(id); // Set the ID from path variable
            flowerServiceImpl.updateFlower(flowerDTO); // Update the FlowerDTO
            return ResponseEntity.ok().body(flowerDTO);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFlowerNow(@PathVariable(value="id") Integer id){
        flowerServiceImpl.deleteFlower(id);
        return ResponseEntity.noContent().build();

        }

}
















