package epitech.emergencity.Vehicle.controllers;

import epitech.emergencity.Vehicle.Vehicle;
import epitech.emergencity.Vehicle.dto.VehicleRequest;
import epitech.emergencity.Vehicle.services.Vehicles;
import epitech.emergencity.security.services.Tokens;
import epitech.emergencity.services.DomainError;
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

@EnableWebSecurity
@RestController
@RequestMapping("/vehicles")
@Slf4j
public class VehicleControllers {
    @Autowired
    Users users;
    @Autowired
    Vehicles vehicles;
    @Autowired
    Tokens tokens;

    private <T> Either<DomainError, T> ifAuthorized(String token, Supplier<Either<DomainError, T>> block) {
        if (tokens.is_admin(token) || tokens.is_super_user(token))
            return block.get();
        else
            return Either.left(DomainError.Unauthorized(null));
    }

    @PostMapping("/new")
    private Object create(@Valid VehicleRequest request, @Valid String token) {
        return API.For(
                tokens.checkToken(token)
        ).yield(check ->
                ifAuthorized(token, () -> Either.right(vehicles.create(request)))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
        /*
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
        */
    }

    @GetMapping("/{id}")
    private Object getById(@PathVariable @Valid @NotNull UUID id, @Valid String token) throws ParseException {
        return API.For(
                tokens.checkToken(token),
                vehicles.getById(id)
        ).yield((user, course) -> course)
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @GetMapping("/registration/{registration}")
    private Object getByRegistration(@PathVariable @Valid @NotNull String registration, @Valid String token) throws ParseException {
        return API.For(
                tokens.checkToken(token),
                vehicles.getByRegistration(registration)
        ).yield((check, course) -> course)
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @DeleteMapping("/{id}")
    private Object deleteById(@PathVariable @Valid @NotNull UUID id, @Valid String token) throws ParseException {
        return API.For(
                tokens.checkToken(token)
        ).yield(check ->
                ifAuthorized(token, () -> Either.right(vehicles.deleteById(id)))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @DeleteMapping("/registration/{registration}")
    private Object deleteByRegistration(@PathVariable @Valid @NotNull String registration, @Valid String token) throws ParseException {
        return API.For(
                tokens.checkToken(token)
        ).yield(check ->
                ifAuthorized(token, () -> Either.right(vehicles.deleteByRegistration(registration)))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @PostMapping("/{id}")
    private Object editById(@PathVariable @Valid @NotNull UUID id, @Valid VehicleRequest request, @Valid String token) throws ParseException {
        return API.For(
                tokens.checkToken(token)
        ).yield(check ->
                ifAuthorized(token, () -> Either.right(vehicles.modById(id, request).get()))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @PostMapping("/registration/{registration}")
    private Object editRegistration(@PathVariable @Valid @NotNull String registration, @Valid VehicleRequest request, @Valid String token) throws ParseException {
        return API.For(
                tokens.checkToken(token)
        ).yield(check ->
                ifAuthorized(token, () -> Either.right(vehicles.modByRegistration(registration, request).get()))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @GetMapping("")
    private Object List(Pageable pageable, @Valid String token) {
        return API.For(
                tokens.checkToken(token)
        ).yield(user -> vehicles.listVehicle(pageable))
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }
}
