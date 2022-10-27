package ChopShop.DAOs.ws;

import ChopShop.DTOs.Animals.Animal;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

public interface Animals {


    Animal create(int id,String type, double weight, String Origin, java.sql.Date date) throws IOException;

    List<Animal> readAll() throws IOException;

    Animal readID(int ID) throws IOException;
    List<Animal> readType(String type) throws IOException;
   List<Animal> readWeight(double weight) throws IOException;
    List<Animal> readOrigin(String origin) throws IOException;
    List<Animal> readDate(Date date) throws IOException;
    void delete(String ID);
}
