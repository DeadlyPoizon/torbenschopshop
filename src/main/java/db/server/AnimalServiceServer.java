package db.server;

import db.DbHelper;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class AnimalServiceServer {
    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder
                .forPort(5555)
                .addService(new AnimalServiceImpl(new DbHelper<>()))
                .build();
        System.out.println("ServerRunning");
        System.out.println(server.toString());

        server.start();

        server.awaitTermination();
    }
}
