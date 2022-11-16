package ChopShop.DAOs;

import ChopShop.DTOs.Animals.Animal;

import java.io.IOException;
import java.util.List;

public class ChopStationDAO {
    private AnimalDAO dao;

    public ChopStationDAO() {
        this.dao = new AnimalDAO();
    }

    public List<Animal> getAllAnimals() throws IOException {
        return dao.readAll();
    }
}
