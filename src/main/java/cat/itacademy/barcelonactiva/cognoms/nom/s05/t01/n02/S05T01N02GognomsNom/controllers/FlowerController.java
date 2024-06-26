package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02GognomsNom.controllers;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02GognomsNom.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02GognomsNom.model.services.FlowerServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/flower")
public class FlowerController {

    @Autowired
    private FlowerServiceImpl flowerServiceImpl;


    @GetMapping({"/", "/getAll"})
    public  String homePage(Model model) {
        model.addAttribute("flowers", flowerServiceImpl.getAllFlowers());
        return "/home";
    }

    @GetMapping({"/add"})
    public String showAddForm(Model model) {
        model.addAttribute("flower", new FlowerDTO());
        return "add";
    }

    @PostMapping({"/add"})
    public String createFlowerAdd(@ModelAttribute("flower") FlowerDTO flowerDTO) {
        flowerServiceImpl.createFlower(flowerDTO);
        return "redirect:/flower/";
    }

    @GetMapping({ "/getOne/{id}"})
    public String showEditForm(@PathVariable(value="id") Integer id, Model model) {
        model.addAttribute("flower", flowerServiceImpl.getFlowerById(id));
        return "edit";
    }

    @PostMapping({ "/update/{id}"})

    public String updateFlowerEdit(@PathVariable("id") Integer id, @ModelAttribute("flower") FlowerDTO flowerDTO, Model model) {
        try {
            flowerDTO.setPkFlowerID(id); // Set the ID from path variable
            flowerServiceImpl.updateFlower(flowerDTO); // Update the FlowerDTO
            return "redirect:/flower/"; // Redirect on success
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "edit"; // Return to edit page with error message
        }
    }

    @PostMapping({"/delete/{id}"})
    public String deleteFlowerForm(@PathVariable(value="id") Integer id){
        flowerServiceImpl.deleteFlower(id);
        return "redirect:/flower/";
    }


}















