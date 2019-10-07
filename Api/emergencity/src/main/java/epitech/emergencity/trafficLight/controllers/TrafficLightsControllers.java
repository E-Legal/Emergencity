package epitech.emergencity.trafficLight.controllers;


import epitech.emergencity.services.DomainError;
import epitech.emergencity.trafficLight.dto.TrafficLightsRequest;
import epitech.emergencity.trafficLight.services.TrafficLights;
import epitech.emergencity.users.services.Users;
import io.vavr.API;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    Users users;

    @PostMapping("")
    private Object create(
            @Valid TrafficLightsRequest request, @Valid String token) {
        return API.For(
                users.UserEgalOfSuppAdmin(token)
        ).yield(user -> trafficLights.create(request.getCity(), request.getX(), request.getY(), request.getZ()))
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @GetMapping("")
    private Object list(Pageable pageable, @Valid String token) {
        return API.For(
                users.checkToken(token)
        ).yield(user -> trafficLights.listTrafficLight(pageable))
                        .toEither(DomainError.Unauthorized(null))
                        .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }
}
