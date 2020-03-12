package epitech.emergencity.Vehicle.controllers;

import epitech.emergencity.Vehicle.Vehicle;
import epitech.emergencity.Vehicle.dto.VehicleRequest;
import epitech.emergencity.Vehicle.services.Vehicles;
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
@RequestMapping("/vehicles")
@Slf4j
public class VehicleControllers {
    @Autowired
    Users users;
    @Autowired
    Vehicles vehicles;

    @PostMapping("/new")
    private Object create(@Valid VehicleRequest request, @Valid String token) {
        return API.For(
                users.UserEgalOfSuppAdmin(token)
        ).yield(user -> {
            Vehicle e = vehicles.create(request);
            if (e == null) {
                DomainError r = (DomainError.BadRequest(null));
                return (ResponseEntity.status(r.getStatus()).body(r.getData()));
            }
            return e;
        })
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @GetMapping("/{id}")
    private Object getById(@PathVariable @Valid @NotNull UUID id, @Valid String token) throws ParseException {
        return API.For(
                users.checkToken(token),
                vehicles.getById(id)
        ).yield((user, course) -> course)
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @GetMapping("/registration/{registration}")
    private Object getByRegistration(@PathVariable @Valid @NotNull String registration, @Valid String token) throws ParseException {
        return API.For(
                users.checkToken(token),
                vehicles.getByRegistration(registration)
        ).yield((user, course) -> course)
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @DeleteMapping("/{id}")
    private Object deleteById(@PathVariable @Valid @NotNull UUID id, @Valid String token) throws ParseException {
        return API.For(
                users.UserEgalOfSuppAdmin(token)
        ).yield((user) -> vehicles.deleteById(id))
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @DeleteMapping("/registration/{registration}")
    private Object deleteByRegistration(@PathVariable @Valid @NotNull String registration, @Valid String token) throws ParseException {
        return API.For(
                users.UserEgalOfSuppAdmin(token)
        ).yield((user) -> vehicles.deleteByRegistration(registration))
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @PostMapping("/{id}")
    private Object editById(@PathVariable @Valid @NotNull UUID id, @Valid VehicleRequest request, @Valid String token) throws ParseException {
        return API.For(
                users.UserEgalOfSuppAdmin(token)
        ).yield((user) -> vehicles.modById(id, request).get())
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @PostMapping("/registration/{registration}")
    private Object editRegistration(@PathVariable @Valid @NotNull String registration, @Valid VehicleRequest request, @Valid String token) throws ParseException {
        return API.For(
                users.UserEgalOfSuppAdmin(token)
        ).yield((user) -> vehicles.modByRegistration(registration, request).get())
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @GetMapping("")
    private Object List(Pageable pageable, @Valid String token) {
        return API.For(
                users.checkToken(token)
        ).yield(user -> vehicles.listVehicle(pageable))
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }
}
