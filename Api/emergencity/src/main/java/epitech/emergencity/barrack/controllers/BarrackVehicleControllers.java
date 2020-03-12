package epitech.emergencity.barrack.controllers;


import epitech.emergencity.barrack.BarrackUser;
import epitech.emergencity.barrack.BarrackVehicle;
import epitech.emergencity.barrack.dto.BarrackUserRequest;
import epitech.emergencity.barrack.dto.BarrackVehicleRequest;
import epitech.emergencity.barrack.services.BarrackUsers;
import epitech.emergencity.barrack.services.BarrackVehicles;
import epitech.emergencity.barrack.services.Barracks;
import epitech.emergencity.services.DomainError;
import epitech.emergencity.users.services.Users;
import io.vavr.API;
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

@EnableWebSecurity
@RestController
@RequestMapping("/barrackVehicles")
@Slf4j
public class BarrackVehicleControllers {

    @Autowired
    Users users;
    @Autowired
    Barracks barracks;
    @Autowired
    BarrackVehicles barrackVehicles;

    @PostMapping("/new")
    private Object create(@Valid BarrackVehicleRequest request, @Valid String token) {
        return API.For(
                users.UserEgalOfSuppAdmin(token)
        ).yield(user -> {
            BarrackVehicle e = barrackVehicles.create(request);
            if (e == null) {
                DomainError r = (DomainError.BadRequest(null));
                return (ResponseEntity.status(r.getStatus()).body(r.getData()));
            }
            return e;
        })
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @DeleteMapping("/delete")
    private Object delete(@Valid @NotNull BarrackVehicleRequest request, @Valid String token) throws ParseException {
        return API.For(
                users.UserEgalOfSuppAdmin(token)
        ).yield((user) -> barrackVehicles.delete(request))
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @GetMapping("/{id}")
    private Object List(@PathVariable @Valid @NotNull UUID id, Pageable pageable, @Valid String token) {
        return API.For(
                users.checkToken(token)
        ).yield(user -> barrackVehicles.listUser(id, pageable))
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }
}
