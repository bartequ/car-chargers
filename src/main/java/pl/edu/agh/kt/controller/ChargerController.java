package pl.edu.agh.kt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.kt.model.Charger;
import pl.edu.agh.kt.model.ChargerDetails;
import pl.edu.agh.kt.service.ChargerService;

import java.util.List;

@RestController
public class ChargerController {

    private ChargerService chargerService;

    @Autowired
    public ChargerController(ChargerService chargerService) {
        this.chargerService = chargerService;
    }

    @PostMapping("kt/charger")
    public Integer addCharger(@RequestBody Charger charger) throws Exception {
        String address = chargerService.localizeAddress(charger.getLatitude(), charger.getLongitude());
        charger.setAddress(address);
        return chargerService.addCharger(charger);
    }

    @GetMapping("/kt/{id}/chargerDetails")
    public ChargerDetails getChargerDetails(@PathVariable Integer id) {
        return chargerService.getChargerDetails(id);
    }

    @GetMapping("/kt")
    public List<Charger> getChargerIdInArea(@RequestParam double latitude, @RequestParam double longitude) {
        return chargerService.getChargerIdInArea(latitude, longitude);
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "Connection to car chargers backend successful";
    }
}
