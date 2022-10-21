package ChopShop.DAOs;

import ChopShop.DAOs.ws.Animals;
import ChopShop.DTOs.Animals.Animal;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SequenceWriter;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
        /*
        File file = new File("jsons/animals.json");

        for (int i = 0; i < animals.size(); i++) {
            if(animal.getID().equals(animals.get(i).getID())){
                System.out.println("ID already exists");
            }
        }
*/
        try {


            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

            File file = new File("src/main/resources/jsons/animals.json");
            FileWriter fileWriter = new FileWriter(file,true);
            SequenceWriter sequenceWriter = writer.writeValuesAsArray(fileWriter);




                sequenceWriter.write(animal);
                sequenceWriter.close();




        } catch (Exception e) {
            e.printStackTrace();
        }

        return animal;
    }

    @Override
    public List<Animal> readAll() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Animal> animals;
        File file = new File("src/main/resources/jsons/animals.json");
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
