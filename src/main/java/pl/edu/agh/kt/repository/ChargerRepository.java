package pl.edu.agh.kt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.kt.model.Charger;

import java.util.Optional;

@Repository
public interface ChargerRepository extends JpaRepository<Charger, Long> {

     Optional<Charger> findByLatitudeAndLongitude(double latitude, double longitude);
}
