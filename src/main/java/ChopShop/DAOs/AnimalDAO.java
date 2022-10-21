package ChopShop.DAOs;

import ChopShop.DAOs.ws.Animals;
import ChopShop.DTOs.Animals.Animal;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AnimalDAO implements Animals {

    private static Animal createAnimalDTO(String ID, double weight, String origin){
        Date date = new Date();

        Animal animal = new Animal();
        animal.setID(ID);
        animal.setWeight(weight);
        animal.setOrigin(origin);
        animal.setDate(date);

/*      ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("jsons/animals").toFile(), animal);*/
        return animal;
    }

    @Override
    public Animal create(String ID, double weight, String Origin) throws IOException {
        final Path path = Path.of("src/main/resources/jsons/animals.json");
        boolean exists = Files.isReadable(path);
        Animal animal = new Animal(ID, weight, Origin);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Animal> animals;

        /*
        File file = new File("jsons/animals.json");

        for (int i = 0; i < animals.size(); i++) {
            if(animal.getID().equals(animals.get(i).getID())){
                System.out.println("ID already exists");
            }
        }
*/

        //if(!exists) {
            objectMapper.writeValue(path.toFile(), animal);
       // }
       /* else{
            animals = Arrays.asList(objectMapper.readValue(path.toFile(), Animal[].class));
            animals.add(animal);
            objectMapper.writeValue(path.toFile(), animals);
        }*/
        return animal;
    }

    @Override
    public List<Animal> readAll() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return Arrays.asList(objectMapper.readValue(Paths.get("src/main/resources/jsons/animals.json").toFile(), Animal[].class));
    }

    @Override
    public Animal read(String ID) {
        return null;
    }

    @Override
    public void delete(String ID) {

    }
}
