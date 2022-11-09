package WebService.controllers;


import ChopShop.DAOs.PartDAO;
import ChopShop.DTOs.Animals.Part;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/animals/parts")
public class PartController {
    private final PartDAO dao;

    public PartController() {
        this.dao = new PartDAO();
    }

    @GetMapping
        public List<Part> readAll() throws IOException{
        return dao.readAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Part create(@RequestBody Part part){
        try{
            return dao.create(part.getAnimalID(), part.getPartName(), part.getWeight());
        }
        catch (Exception e){
            e.getStackTrace();
        }
        return part;
    }

}
