package WebService.controllers;

import ChopShop.DAOs.AnimalDAO;
import ChopShop.DTOs.Animals.Animal;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {
   private final AnimalDAO dao;


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
            return dao.create(animal.getId(), animal.getWeight(), animal.getOrigin(), animal.getDate(), animal.getType());
        }
        catch (Exception e){
            e.getStackTrace();
        }
        return animal;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/id/{id}")
    public Animal readID( @PathVariable("id") int id) throws IOException {
    return dao.readID(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/type/{type}")
    public List<Animal> readType( @PathVariable("type") String type) throws IOException {
        return dao.readType(type);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/weight/{weight}")
    public List<Animal> readWeight( @PathVariable("weight") double weight) throws IOException {
        return dao.readWeight(weight);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/origin/{origin}")
    public List<Animal> readOrigin( @PathVariable("origin") String origin) throws IOException {
        return dao.readOrigin(origin);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/date/{date}")
    public List<Animal> readDate( @PathVariable("date") Date date) throws IOException {
        return dao.readDate(date);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        dao.delete(id);
    }


}
