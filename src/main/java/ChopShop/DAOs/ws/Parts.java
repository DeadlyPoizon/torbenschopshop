package ChopShop.DAOs.ws;

import ChopShop.DTOs.Animals.Part;

import java.io.IOException;
import java.util.List;

public interface Parts {

   Part create(int animalId, String partname, double weight);

   List<Part> readAll() throws IOException;

   Part readID(int animalID);

   void delete(int animalID);
}
