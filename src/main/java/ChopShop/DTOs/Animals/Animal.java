package ChopShop.DTOs.Animals;

import java.io.Serializable;
import java.util.Date;

public class Animal implements Serializable {
    private String ID;
    private double weight;
    private String origin;
    private Date date;

    public Animal(String ID, double weight, String origin) {
        this.ID = ID;
        this.weight = weight;
        this.origin = origin;
        this.date = new Date();
    }

    public Animal(){
        //
    }

    public Animal(String ID, double weight, String origin, Date date) {
        this.ID = ID;
        this.weight = weight;
        this.origin = origin;
        this.date = date;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
