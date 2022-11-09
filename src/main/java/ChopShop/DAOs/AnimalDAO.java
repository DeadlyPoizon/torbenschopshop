package ChopShop.DAOs;

import ChopShop.DAOs.ws.Animals;
import ChopShop.DTOs.Animals.Animal;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import db.DataMapper;
import db.DbHelper;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.aspectj.weaver.ast.Or;
import src.main.java.grpc.AnimalRequest;
import src.main.java.grpc.AnimalRequestServiceGrpc;

import javax.persistence.Id;
import java.io.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AnimalDAO implements Animals {

    public AnimalDAO() {
    }

    private static Animal createAnimalDTO(int id, double weight, String origin, java.sql.Date date, String type){

        Animal animal = new Animal();
        animal.setId(id);
        animal.setType(type);
        animal.setWeight(weight);
        animal.setOrigin(origin);
        animal.setDate(date);

        return animal;
    }

    private ManagedChannel connect(){
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 5555)
                .usePlaintext()
                .build();
        return managedChannel;
    }

    @Override
    public Animal create(int id, double weight, String Origin, java.sql.Date date, String type) throws IOException {

       ManagedChannel channel = connect();
        System.out.println(channel.toString());
        System.out.println("connected");

        AnimalRequestServiceGrpc.AnimalRequestServiceBlockingStub blockingStub =
                AnimalRequestServiceGrpc.newBlockingStub(channel);
        System.out.println("stubbed");

        AnimalRequest animalRequest = AnimalRequest.newBuilder()
                .setId(String.valueOf(id))
                .setWeight(weight)
                .setOrigin(Origin)
                .setDate(date.toString())
                .setType(type)
                .build();
        System.out.println(animalRequest.toString());
        System.out.println("built");

        src.main.java.grpc.response response = blockingStub.grpc(animalRequest);
        System.out.println("Success!");
        channel.shutdown();
        return createAnimalDTO(id,weight,Origin,date, type);
    }

    private static class AnimalMapper implements DataMapper<Animal> {
        public Animal create(ResultSet rs) throws SQLException {
            int id = rs.getInt("id");
            String type = rs.getString("type");
            double weight = rs.getDouble("weight");
            String origin = rs.getString("origin");
            java.sql.Date date = rs.getDate("date");

            return createAnimalDTO(id,weight,origin, date, type);
        }
    }

    @Override
    public List<Animal> readAll() throws IOException {

        return null;
       // return db.map(new AnimalMapper(),"SELECT * FROM animal");


    }



    @Override
    public Animal readID(int id) throws IOException {
        return null;
        //return db.mapSingle(new AnimalMapper(), "SELECT * FROM animal WHERE id = ?", id);

    }
    @Override
    public List<Animal> readType(String type) throws IOException {
       return null;
       // return db.map(new AnimalMapper(), "SELECT * FROM animal WHERE type = ?", type);
    }

    @Override
    public List<Animal> readWeight(double weight) throws IOException {
       return null;
        // return db.map(new AnimalMapper(), "SELECT * FROM animal WHERE weight = ?", weight);

    }

    @Override
    public List<Animal> readOrigin(String origin) throws IOException {
       return null;
       // return db.map(new AnimalMapper(), "SELECT * FROM animal WHERE origin = ?", origin);

    }

    @Override
    public List<Animal> readDate(Date date) throws IOException {
        return null;
        //return db.map(new AnimalMapper(), "SELECT * FROM animal WHERE date = ?", date);

    }

    @Override
    public void delete(String ID) {
//db.executeUpdate("DELETE FROM animal WHERE id = ?",ID);
    }
}
