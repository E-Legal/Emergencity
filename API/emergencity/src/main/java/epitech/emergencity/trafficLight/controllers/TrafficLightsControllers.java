package epitech.emergencity.trafficLight.controllers;


import epitech.emergencity.security.services.Tokens;
import epitech.emergencity.services.DomainError;
import epitech.emergencity.trafficLight.dto.TrafficLightsRequest;
import epitech.emergencity.trafficLight.services.TrafficLights;
import epitech.emergencity.users.services.Users;
import io.vavr.API;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.jooq.tools.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;
import java.util.function.Supplier;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableWebSecurity
@RestController
@RequestMapping("/feu")
@Slf4j
public class TrafficLightsControllers {

    @Autowired
    TrafficLights trafficLights;
    @Autowired
    Users users;
    @Autowired
    Tokens tokens;

    private <T> Either<DomainError, T> ifAuthorized(String token, Supplier<Either<DomainError, T>> block) {
        if (tokens.is_admin(token) || tokens.is_super_user(token))
            return block.get();
        else
            return Either.left(DomainError.Unauthorized(null));
    }

    @PostMapping("")
    private Object create(@Valid TrafficLightsRequest request, @Valid String token) {
        return API.For(
                tokens.checkToken(token)
        ).yield(check ->
                ifAuthorized(token, () -> Either.right(trafficLights.create(request.getCity(), request.getX(), request.getY(), request.getZ())))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @GetMapping("")
    private Object list(Pageable pageable, @Valid String token) {
        return API.For(
                tokens.checkToken(token)
        ).yield(user -> trafficLights.listTrafficLight(pageable))
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @DeleteMapping("/{id}")
    private Object delete(@PathVariable @Valid @NotNull UUID id, @Valid String token) throws ParseException {
        return API.For(
                tokens.checkToken(token)
        ).yield(check ->
                ifAuthorized(token, () -> Either.right(trafficLights.delete(id)))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }
}