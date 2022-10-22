package ChopShop.DAOs.ws;

import ChopShop.DTOs.Animals.Animal;

import java.io.IOException;
import java.util.List;

public interface Animals {
    Animal create(String ID, double weight, String Origin) throws IOException;
    List<Animal> readAll() throws IOException;

    Animal read(String ID) throws IOException;
    void delete(String ID);
}
