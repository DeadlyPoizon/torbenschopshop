package WebService.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import src.main.java.grpc.AnimalRequest;
import src.main.java.grpc.AnimalRequestServiceGrpc;

public class ChopShopClient {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 5555)
                .usePlaintext()
                .build();

        AnimalRequestServiceGrpc.AnimalRequestServiceBlockingStub blockingStub =
                AnimalRequestServiceGrpc.newBlockingStub(managedChannel);

        AnimalRequest animalRequest = AnimalRequest.newBuilder()
                .setId("ClientTest")
                .setOrigin("Client")
                .build();

        src.main.java.grpc.response response = blockingStub.grpc(animalRequest);
        System.out.println(response.getResponse());
        managedChannel.shutdown();
    }
}
