package db.server;

import ChopShop.DTOs.Animals.Animal;
import db.DbHelper;
import io.grpc.stub.StreamObserver;
import src.main.java.grpc.AnimalRequest;
import src.main.java.grpc.AnimalRequestServiceGrpc;
import src.main.java.grpc.response;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;

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

}
