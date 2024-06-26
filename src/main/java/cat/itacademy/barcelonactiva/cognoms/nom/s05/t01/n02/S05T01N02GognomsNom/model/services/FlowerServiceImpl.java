package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02GognomsNom.model.services;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02GognomsNom.model.domain.Flower;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02GognomsNom.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02GognomsNom.model.repository.IflowerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service



public class FlowerServiceImpl implements Iflower {
    private IflowerRepository iFlowerRepository;
    @Autowired
    public FlowerServiceImpl(IflowerRepository iFlowerRepository){
        this.iFlowerRepository = iFlowerRepository;
    }
    private Flower toEntity(FlowerDTO flowerDTO){
        if(flowerDTO.getPkFlowerID()==null){
            return new Flower(flowerDTO.getNameFlower(), flowerDTO.getCountryFlower());
        }
        return new Flower(flowerDTO.getPkFlowerID(), flowerDTO.getNameFlower(), flowerDTO.getCountryFlower());

    }
    private FlowerDTO toDTO(Flower flower){
        return  new FlowerDTO(flower.getPkFlowerId(), flower.getNameFlower(), flower.getCountryFlower());
    }



    @Override
    public void createFlower(FlowerDTO flowerDTO) {

        iFlowerRepository.save(toEntity(flowerDTO));
    }

    @Override
    @Transactional
    public void updateFlower(FlowerDTO flowerDTO) {
        Optional<Flower> existingFlowerOptional = iFlowerRepository.findById(flowerDTO.getPkFlowerID());

        if (!existingFlowerOptional.isPresent()) {
            throw new EntityNotFoundException("Update Flower Failed: Invalid ID: " + flowerDTO.getPkFlowerID() +
                    " -> DOESN'T EXIST in DataBase");
        }

        // If the entity exists, proceed with updating its fields
        Flower existingFlower = existingFlowerOptional.get();
        existingFlower.setNameFlower(flowerDTO.getNameFlower());
        existingFlower.setCountryFlower(flowerDTO.getCountryFlower());


        iFlowerRepository.save(existingFlower);
    }

    @Override
    @Transactional
    public void deleteFlower(Integer flowerId) {
        if(!iFlowerRepository.findById(flowerId).isPresent()){
            throw new EntityNotFoundException("Update Flower Failed: Invalid ID: "+ flowerId+
                    " -> DOESN'T EXIST in DataBase");
        }
        iFlowerRepository.deleteById(flowerId);
        }

    @Override
    @Transactional(readOnly = true)
    public FlowerDTO getFlowerById(Integer flowerId) {
        Flower flower = iFlowerRepository.findById(flowerId)
                .orElseThrow(() -> new NoResultException("Get One Flower Failed: Invalid ID: "+ flowerId
                        +" -> DOESN'T EXIST in DataBase"));

        return toDTO(flower);
    }


    @Override
    @Transactional(readOnly = true)
    public List<FlowerDTO> getAllFlowers() {
        List<Flower> flowerList = iFlowerRepository.findAll();

        return flowerList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}