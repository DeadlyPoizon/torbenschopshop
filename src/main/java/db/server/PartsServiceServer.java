package db.server;

import db.DbHelper;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class PartsServiceServer {
    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder
                .forPort(6666)
                .addService(new PartsServiceImpl(new DbHelper<>()))
                .build();
        System.out.println("ServerRunning");
        System.out.println(server.toString());

        server.start();

        server.awaitTermination();
    }
}
