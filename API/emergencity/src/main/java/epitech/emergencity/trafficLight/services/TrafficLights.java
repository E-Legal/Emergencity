package epitech.emergencity.trafficLight.services;

import epitech.emergencity.trafficLight.TrafficLight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TrafficLights {
    TrafficLight create(String city, String x, String y, String z);
    Page<TrafficLight> listTrafficLight(Pageable pageable);
    Boolean delete(UUID id);
}
