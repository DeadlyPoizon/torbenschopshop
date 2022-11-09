package ChopShop.DAOs;

import ChopShop.DAOs.ws.Parts;
import ChopShop.DTOs.Animals.Part;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import src.main.java.grpc.PartRequestServiceGrpc;
import src.main.java.grpc.PartsRequest;

import java.io.IOException;
import java.util.List;

public class PartDAO implements Parts {

    public PartDAO() {
    }

    private static Part createPartDTO(int animalID, String partname, double weight){
        Part part = new Part();
        part.setAnimalID(animalID);
        part.setPartName(partname);
        part.setWeight(weight);
        return part;
    }

    private ManagedChannel connect(){
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 6666)
                .usePlaintext()
                .build();

        return managedChannel;
    }

    @Override
    public Part create(int animalId, String partname, double weight) {

        ManagedChannel channel = connect();
        System.out.println("Connected");

        PartRequestServiceGrpc.PartRequestServiceBlockingStub blockingStub = PartRequestServiceGrpc.newBlockingStub(channel);
        System.out.println("stubbed");

        PartsRequest partsRequest = PartsRequest.newBuilder()
                .setAnimalid(String.valueOf(animalId))
                .setPartname(partname)
                .setWeight(weight)
                .build();
        System.out.println(partsRequest.toString());
        System.out.println("built");

        src.main.java.grpc.response response = blockingStub.partsgrpc(partsRequest);
        System.out.println("Success");
        channel.shutdown();
        return createPartDTO(animalId, partname, weight);
    }

    @Override
    public List<Part> readAll() throws IOException {
        return null;
    }

    @Override
    public Part readID(int animalID) {
        return null;
    }

    @Override
    public void delete(int animalID) {

    }
}
