package ChopShop.DAOs;

import ChopShop.DAOs.ws.Animals;
import ChopShop.DTOs.Animals.Animal;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
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
        objectMapper.writeValue(new File("jsons/animals"), animal);*/
        return animal;
    }

    @Override
    public Animal create(String ID, double weight, String Origin) throws IOException {
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

        objectMapper.writeValue(new File("jsons/animals.json"), animal);

        return animal;
    }

    @Override
    public List<Animal> readAll() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Animal> animals;
        File file = new File("jsons/animals.json");
        animals = Arrays.asList(objectMapper.readValue(file, Animal[].class));

        return animals;
    }

    @Override
    public Animal read(String ID) {
        return null;
    }

    @Override
    public void delete(String ID) {

    }
}
