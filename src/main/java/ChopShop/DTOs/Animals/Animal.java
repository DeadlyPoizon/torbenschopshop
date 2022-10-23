package ChopShop.DTOs.Animals;

import java.io.Serializable;
import java.sql.Date;

public class Animal implements Serializable {
    private String id;
    private double weight;
    private String origin;
    private java.sql.Date date;


    public Animal(){
        //
    }

    public Animal(String id, double weight, String origin, Date date) {
        this.id = id;
        this.weight = weight;
        this.origin = origin;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }


}
