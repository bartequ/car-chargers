package pl.edu.agh.kt.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.edu.agh.kt.exception.CannotBuildAddress;
import pl.edu.agh.kt.exception.CannotFetchAddressInfo;
import pl.edu.agh.kt.exception.ResourceNotFound;
import pl.edu.agh.kt.model.Charger;
import pl.edu.agh.kt.model.ChargerCoordinates;
import pl.edu.agh.kt.model.ChargerDetails;
import pl.edu.agh.kt.repository.ChargerRepository;

import java.util.Objects;

@Service
public class ChargerService {

    private ChargerRepository chargerRepository;

    @Autowired
    public ChargerService(ChargerRepository chargerRepository) {
        this.chargerRepository = chargerRepository;
    }

    public Charger addCharger(Charger charger) {
        return chargerRepository.save(charger);
    }

    public ChargerDetails getChargerDetails(long id)  {
        return chargerRepository
                .findById(id)
                .map(c -> new ChargerDetails(c.getId(), c.getAddress(), c.getRating(), c.getName(), c.getCost()))
                .orElseThrow(() -> new ResourceNotFound("Resource not found with id " + id));
    }

    public ChargerCoordinates getChargerIdByCoordinates(double latitude, double longitude) {
        return chargerRepository
                .findByLatitudeAndLongitude(latitude, longitude)
                .map(c -> new ChargerCoordinates(c.getId(), c.getLatitude(), c.getLongitude()))
                .orElseThrow( () -> new ResourceNotFound("Resource not found with lat: " + latitude + "and lon:" + longitude));
    }

    public String localizeAddress(double latitude, double longitude) throws Exception {
        final String uri = "https://nominatim.openstreetmap.org/reverse?lat=" + latitude +
                            "&lon=" + longitude + "&accept-language=polish&format=json&email=bszabat12@wp.pl";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        if (response.getStatusCode().value() != 200) {
            throw new CannotFetchAddressInfo("Cannot fetch address data (based on lat and lon) from external API.");
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(Objects.requireNonNull(response.getBody()));
        JsonNode data = mapper.readTree(parser);

        JsonNode address = data.get("address");
        if (address.get("city") != null && address.get("road") != null) {
            if (address.get("house_number") != null)
                return buildAddress(address, "city", "road", "house_number");
            return buildAddress(address, "city", "road");
        }
        else if (address.get("city") != null && address.get("pedestrian") != null) {
            if (address.get("address29") != null)
                return buildAddress(address, "city", "pedestrian", "address29");
            return buildAddress(address, "city", "pedestrian");
        }
        throw new CannotBuildAddress("Cannot translate coordinates into addres");
    }

    private String buildAddress(JsonNode address, String x, String y) {
        return address.get(x).toString().replace("\"","") + ", " + address.get(y).toString().replace("\"","");
    }

    private String buildAddress(JsonNode address, String x, String y, String z) {
        return address.get(x).toString().replace("\"","") + ", " + address.get(y).toString().replace("\"","") + ", " + address.get(z).toString().replace("\"","");
    }
}
