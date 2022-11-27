package db.server;

import ChopShop.DAOs.AnimalDAO;
import ChopShop.DTOs.Animals.Animal;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.DataMapper;
import db.DbHelper;
import io.grpc.stub.StreamObserver;
import src.main.java.grpc.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnimalServiceImpl extends AnimalRequestServiceGrpc.AnimalRequestServiceImplBase {

    private  DbHelper dbHelper;
    public AnimalServiceImpl(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public void grpc(AnimalRequest request, StreamObserver<response> responseStreamObserver){
        System.out.println("received");
        String response = String.format("Hello, %s %s!", request.getId(), request.getOrigin());
        dbHelper.executeUpdate("INSERT INTO slaughter_house.animal VALUES  (?, ?, ?, ?, ?)", Integer.parseInt(request.getId()), request.getWeight(), request.getOrigin(), Date.valueOf(request.getDate()), request.getType());
        System.out.println("saved");
        src.main.java.grpc.response response1 = src.main.java.grpc.response.newBuilder()
                .setResponse(response)
                .build();
        System.out.println("built");
        responseStreamObserver.onNext(response1);
        responseStreamObserver.onCompleted();

    }

    @Override
    public void getAll(AllAnimalsRequest request, StreamObserver<AllAnimals> streamObserver){
        List<Animal> animals;

        if(request.getRequest().equals("all")) {
            animals = dbHelper.map(new AnimalMapper(), "SELECT * FROM animal");
        }
        else {
            animals = dbHelper.map(new AnimalMapper(), "SELECT * FROM animal WHERE id = ?", request.getRequest());
        }
        String responseJSON;
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            responseJSON = objectMapper.writeValueAsString(animals);
            src.main.java.grpc.AllAnimals allAnimals = src.main.java.grpc.AllAnimals.newBuilder()
                    .setResponse(responseJSON)
                    .build();

            streamObserver.onNext(allAnimals);
            streamObserver.onCompleted();
        }
        catch (JsonProcessingException e){
            e.getStackTrace();
        }

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

    private static Animal createAnimalDTO(int id, double weight, String origin, java.sql.Date date, String type){

        Animal animal = new Animal();
        animal.setId(id);
        animal.setType(type);
        animal.setWeight(weight);
        animal.setOrigin(origin);
        animal.setDate(date);

        return animal;
    }
}
