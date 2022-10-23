package ChopShop.DAOs;

import ChopShop.DAOs.ws.Animals;
import ChopShop.DTOs.Animals.Animal;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import db.DataMapper;
import db.DbHelper;

import java.io.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AnimalDAO implements Animals {

    private final DbHelper<Animal> db;

    private final static String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres?currentSchema=slaughter_house";

    private final static String USERNAME = "postgres";      //Indsæt dine datebase loginoplysninger her Ole :)

    private final static String PASSWORD = "6969420";

    public AnimalDAO() {
        this.db = new DbHelper<>(JDBC_URL, USERNAME, PASSWORD);
    }

    private static Animal createAnimalDTO(String ID, double weight, String origin, java.sql.Date date){

        Animal animal = new Animal();
        animal.setId(ID);
        animal.setWeight(weight);
        animal.setOrigin(origin);
        animal.setDate(date);

        return animal;
    }

    @Override
    public Animal create(String ID, double weight, String Origin, java.sql.Date date) throws IOException {

        db.executeUpdate("INSERT INTO animal VALUES (?, ?, ?, ?)", ID, weight, Origin,date);
        return createAnimalDTO(ID,weight,Origin,date);

    }

    private static class AnimalMapper implements DataMapper<Animal> {
        public Animal create(ResultSet rs) throws SQLException {
            String id = rs.getString("id");
            double weight = rs.getDouble("weight");
            String origin = rs.getString("origin");
            java.sql.Date date = rs.getDate("date");

            return createAnimalDTO(id,weight,origin, date);
        }
    }

    @Override
    public List<Animal> readAll() throws IOException {
        return db.map(new AnimalMapper(),"SELECT * FROM animal");


    }

    @Override
    public Animal readID(String id) throws IOException {
        return db.mapSingle(new AnimalMapper(), "SELECT * FROM animal WHERE id = ?", id);

    }

    @Override
    public List<Animal> readWeight(double weight) throws IOException {
        return db.map(new AnimalMapper(), "SELECT * FROM animal WHERE weight = ?", weight);

    }

    @Override
    public List<Animal> readOrigin(String origin) throws IOException {
        return db.map(new AnimalMapper(), "SELECT * FROM animal WHERE origin = ?", origin);

    }

    @Override
    public List<Animal> readDate(Date date) throws IOException {
        return db.map(new AnimalMapper(), "SELECT * FROM animal WHERE date = ?", date);

    }

    @Override
    public void delete(String ID) {
db.executeUpdate("DELETE FROM animal WHERE id = ?",ID);
    }
}
