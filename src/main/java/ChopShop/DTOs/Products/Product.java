package ChopShop.DTOs.Products;

public class Product {
    private String ID;
    private String part;
    private double weight;

    public Product(String ID, String part, double weight) {
        this.ID = ID;
        this.part = part;
        this.weight = weight;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ID='" + ID + '\'' +
                ", part='" + part + '\'' +
                ", weight=" + weight +
                '}';
    }
}
