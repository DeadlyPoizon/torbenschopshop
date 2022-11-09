package db.server;

import db.DbHelper;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import src.main.java.grpc.PartRequestServiceGrpc;
import src.main.java.grpc.PartsRequest;
import src.main.java.grpc.response;

public class PartsServiceImpl extends PartRequestServiceGrpc.PartRequestServiceImplBase {
    DbHelper dbHelper;
    public PartsServiceImpl(DbHelper dbHelper) {
     this.dbHelper = dbHelper;
    }

    @Override
    public void partsgrpc(PartsRequest request, StreamObserver<response> responseStreamObserver){
        System.out.println("received");
        String response = String.format("Hello, %s %s!", request.getAnimalid(), request.getWeight());
        dbHelper.executeUpdate("INSERT INTO slaughter_house.parts VALUES  (?, ?, ?)", Integer.parseInt(request.getAnimalid()), request.getPartname(), request.getWeight());
        System.out.println("saved");
        src.main.java.grpc.response response1 = src.main.java.grpc.response.newBuilder()
                .setResponse(response)
                .build();
        System.out.println("built");
        responseStreamObserver.onNext(response1);
        responseStreamObserver.onCompleted();

    }

}
