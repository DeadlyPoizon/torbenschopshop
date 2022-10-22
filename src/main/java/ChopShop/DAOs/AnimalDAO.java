package ChopShop.DAOs;

import ChopShop.DAOs.ws.Animals;
import ChopShop.DTOs.Animals.Animal;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.net.URL;
import java.util.*;

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
    public Animal read(String ID) throws IOException { // Christian if you read this, så tror jeg den her read metode virker, jeg ved bare ikke lige hvordan jeg kan teste den i postman ;(

        ObjectMapper reader = new ObjectMapper();
        List<Animal> animal = new ArrayList<>();
        File file = new File("src/main/resources/jsons/animals.json");
        ObjectNode node = reader.readValue(file,ObjectNode.class);

        if(node.has(ID)){
          Animal jsonNode = reader.treeToValue(node,Animal.class);
          animal.add(jsonNode);
            System.out.println(animal.get(0));
        }

return animal.get(0);

    }

    @Override
    public void delete(String ID) {

    }
}
