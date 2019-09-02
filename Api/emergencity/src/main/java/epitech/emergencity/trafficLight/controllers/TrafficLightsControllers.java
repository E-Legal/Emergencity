package epitech.emergencity.trafficLight.controllers;


import epitech.emergencity.trafficLight.TrafficLight;
import epitech.emergencity.trafficLight.dto.TrafficLightsRequest;
import epitech.emergencity.trafficLight.services.TrafficLights;
import epitech.emergencity.users.dto.UsersRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@EnableWebSecurity
@RestController
@RequestMapping("/feu")
@Slf4j
public class TrafficLightsControllers {

    @Autowired
    TrafficLights trafficLights;

    @PostMapping("")
    private Object create(
            @Valid TrafficLightsRequest request) {
        return trafficLights.create(request.getCity(), request.getX(), request.getY(), request.getZ()
        );
    }

    @GetMapping("")
    private Object list(Pageable pageable) {
        return trafficLights.listTrafficLight(pageable);
    }
}
