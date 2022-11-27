package ChopShop.DTOs.Animals;

public class Package {

    private String animalID;
    private double weight;
    private String partName;
    private int trayId;

    public Package(String animalID, double weight, String partName, int trayId) {
        this.animalID = animalID;
        this.weight = weight;
        this.partName = partName;
        this.trayId = trayId;
    }

    public Package(Part part, int trayId) {
        this.animalID = String.valueOf(part.getAnimalID());
        this.weight = part.getWeight();
        this.partName = part.getPartName();
    }

    public String getAnimalID() {
        return animalID;
    }

    public void setAnimalID(String animalID) {
        this.animalID = animalID;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }
}
