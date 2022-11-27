package ChopShop.DAOs;

import ChopShop.DTOs.Animals.Animal;
import ChopShop.DTOs.Animals.Package;
import ChopShop.DTOs.Animals.Part;
import ChopShop.Misc.PackingStation;
import ChopShop.Misc.Tray;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Pack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChopStationDAO {
    private AnimalDAO dao;
    private PartDAO pDAO;
    private List<Tray> trays = new ArrayList<>(100);

    public ChopStationDAO() {
        this.dao = new AnimalDAO();
        this.pDAO = new PartDAO();
    }

    public List<Animal> getAllAnimals() throws IOException {
        return dao.readAll();
    }


    public List<Part> slaughterAnimal(String animalId) throws IOException {
        Animal animal = dao.readID(animalId);
        List<Part> parts = null;

        switch (animal.getType()){
            case "cow":
                parts = slaughterCow(animal);
                break;
            case "pig":
                parts = slaughterPig(animal);
                break;
            case "chicken":
                parts = slaughterChicken(animal);
                break;
        }

        for (int i = 0; i < parts.size(); i++) {
            pDAO.create(parts.get(i).getAnimalID(), parts.get(i).getPartName(), parts.get(i).getWeight());
        }

        assert parts != null;
        usePartsTray(parts);

        return parts;
    }

    private void usePartsTray(List<Part> parts){
        for (int i = 0; i < parts.size(); i++) {
            trays.add(new Tray());
        }

            for (int i = 0; i < trays.size(); i++) {
                trays.get(i).setParts(parts.get(i));
            }

        PackingStation packingStation = new PackingStation(trays);
           List<Package> packages = packingStation.createPackages();
        System.out.println(packages.toString());

    }


    private List<Part> slaughterPig(Animal animal){
        String animalID = String.valueOf(animal.getId());
        List<Part> parts = new ArrayList<>();

        String[] cuts = {"Kotelleter", "Lårben", "Flæskesteg", "Ribben"};

        for (int i = 0; i < cuts.length; i++) {
            parts.add(new Part(Integer.parseInt(animalID), cuts[i],  (int) ( 1 + Math.random() * 10)));
        }

        return parts;

    }
    private List<Part> slaughterCow(Animal animal){
        String animalID = String.valueOf(animal.getId());
        List<Part> parts = new ArrayList<>();

        String[] cuts = {"Sirloin", "Brisket", "Flankesteak", "Hakkebøffer"};

        for (int i = 0; i < cuts.length; i++) {
            parts.add(new Part(Integer.parseInt(animalID), cuts[i], (int) (1 + Math.random() * 10)));
        }

        return parts;

    }
        private List<Part> slaughterChicken(Animal animal){
        String animalID = String.valueOf(animal.getId());
        List<Part> parts = new ArrayList<>();

        String[] cuts = {"Overlår", "Underlår", "Nuggets", "Bryst"};

        for (int i = 0; i < cuts.length; i++) {
            parts.add(new Part(Integer.parseInt(animalID), cuts[i], (int) (1 + Math.random() * 10)));
        }

        return parts;

    }
}
