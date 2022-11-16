package WebService.controllers;


import ChopShop.DAOs.ChopStationDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Chopstation")
public class ChopStationController {
    private ChopStationDAO dao;

    public ChopStationController() {
        this.dao = new ChopStationDAO();
    }


}
