package WebService.controllers;

import ChopShop.DAOs.AnimalDAO;
import ChopShop.DTOs.Animals.Animal;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {
   private final AnimalDAO dao;
    private ArrayList<Animal> animals = new ArrayList<>();


    public AnimalController() {
    this.dao = new AnimalDAO();
    }

    @GetMapping
    public List<Animal> readAll() throws IOException {
       return dao.readAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Animal create(@RequestBody Animal animal){
        try{
            return dao.create(animal.getID(), animal.getWeight(), animal.getOrigin());
        }
        catch (Exception e){
            e.getStackTrace();
        }
        return animal;
    }

    @GetMapping(value = "/{id}")
    public Animal read( @PathVariable("id") String id) throws IOException {
        Animal animal = dao.read(id);
       return animal;
    }


}
