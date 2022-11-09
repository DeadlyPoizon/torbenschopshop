package ChopShop.DTOs.Animals;

public class Part {

    private int animalID;
    private String partName;
    private double weight;

    public Part() {
    }

    public Part(int animalID, String partName, double weight) {
        this.animalID = animalID;
        this.partName = partName;
        this.weight = weight;
    }

    public int getAnimalID() {
        return animalID;
    }

    public void setAnimalID(int animalID) {
        this.animalID = animalID;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
