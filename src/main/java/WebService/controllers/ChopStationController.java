package WebService.controllers;


import ChopShop.DAOs.ChopStationDAO;
import ChopShop.DTOs.Animals.Animal;
import ChopShop.DTOs.Animals.Part;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Chopstation")
public class ChopStationController {
    private ChopStationDAO dao;

    public ChopStationController() {
        this.dao = new ChopStationDAO();
    }


    @GetMapping
    public List<Animal> readAll() throws IOException {
           return dao.getAllAnimals();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Part> slaughterAnimal(@RequestBody String animalId){
        System.out.println("Dyr med ID: '" + animalId + "' bliver slagtet");
        List<Part> parts = new ArrayList<>();
        try{
            parts = dao.slaughterAnimal(animalId);
        }
        catch (Exception e){
            e.getStackTrace();
        }
        return parts;
    }



}
