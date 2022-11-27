package ChopShop.Misc;

import ChopShop.DTOs.Animals.Package;

import java.util.ArrayList;
import java.util.List;

public class PackingStation {
    
    private List<Tray> trays;
    private List<Package> packages = new ArrayList<>();

    public PackingStation(List<Tray> trays) {
        this.trays = trays;
    }

    public List<Tray> getTrays() {
        return trays;
    }

    public void setTrays(List<Tray> trays) {
        this.trays = trays;
    }
    
    public List<Package> createPackages(){
        for (int i = 0; i < trays.size(); i++) {
            packages.add(new Package(trays.get(i).getPart(), trays.get(i).getID()));
        }
        return packages;
    }
}
