package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02GognomsNom.model.services;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02GognomsNom.model.dto.FlowerDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface Iflower {

    void createFlower(FlowerDTO flowerDTO);

    @Transactional
    void updateFlower(FlowerDTO flowerDTO);

    boolean deleteFlower(Integer id);

    FlowerDTO getFlowerById(Integer id);

    List<FlowerDTO> getAllFlowers();


}

