package ChopShop.Misc;

import ChopShop.DTOs.Animals.Part;

import java.time.Instant;

public class Tray {

    private Part part;
    private int ID;

    public Tray() {
        this.ID = Instant.now().getNano();
    }

    public Part getPart() {
        return part;
    }

    public void setParts(Part part) {
        if(!this.part.getPartName().equals(part.getPartName())){
            this.part = part;
        }
        else {
            System.out.println("Part passer ikke til tray");
        }
    }

    public void clearTray(){
        this.part = null;
    }


    public int getID() {
        return ID;
    }
}
