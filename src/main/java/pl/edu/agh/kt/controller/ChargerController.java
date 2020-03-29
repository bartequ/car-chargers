package pl.edu.agh.kt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.kt.model.Charger;
import pl.edu.agh.kt.model.ChargerCoordinates;
import pl.edu.agh.kt.model.ChargerDetails;
import pl.edu.agh.kt.service.ChargerService;

@RestController
public class ChargerController {

    //TODO change name of endpoints
    private ChargerService chargerService;

    @Autowired
    public ChargerController(ChargerService chargerService) {
        this.chargerService = chargerService;
    }

    @PostMapping("kt/toilets")
    public Charger addCharger(@RequestBody Charger charger) throws Exception {
        String address = chargerService.localizeAddress(charger.getLatitude(), charger.getLongitude());
        charger.setAddress(address);
        return chargerService.addCharger(charger);
    }

    @GetMapping("/kt/{id}/basicDetail")
    public ChargerDetails getChargerDetails(@PathVariable Long id) {
        return chargerService.getChargerDetails(id);
    }

    @GetMapping("/kt")
    public ChargerCoordinates getChargerIdByCoordinates(@RequestParam double latitude, @RequestParam double longitude) {
        return chargerService.getChargerIdByCoordinates(latitude, longitude);
    }
}
