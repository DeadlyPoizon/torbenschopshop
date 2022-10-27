package ChopShop.DTOs.Animals;

import java.io.Serializable;
import java.sql.Date;

public class Animal implements Serializable {
    private int id;

    private String type;
    private double weight;
    private String origin;
    private java.sql.Date date;


    public Animal(){
        //
    }

    public Animal(int id, String type, double weight, String origin, Date date) {
        this.id = id;
        this.type = type;
        this.weight = weight;
        this.origin = origin;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(int id) {
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
