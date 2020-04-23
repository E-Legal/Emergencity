package epitech.emergencity.barrack.controllers;


import epitech.emergencity.barrack.BarrackUser;
import epitech.emergencity.barrack.BarrackVehicle;
import epitech.emergencity.barrack.dto.BarrackUserRequest;
import epitech.emergencity.barrack.dto.BarrackVehicleRequest;
import epitech.emergencity.barrack.services.BarrackUsers;
import epitech.emergencity.barrack.services.BarrackVehicles;
import epitech.emergencity.barrack.services.Barracks;
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

@CrossOrigin(origins = "*", maxAge = 3600)
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
    @Autowired
    Tokens tokens;


    private <T> Either<DomainError, T> ifAuthorized(String token, Supplier<Either<DomainError, T>> block) {
        if(tokens.is_admin(token) || tokens.is_super_user(token))
            return block.get();
        else
            return Either.left(DomainError.Unauthorized(null));
    }

    @PostMapping("/new")
    private Object create(@Valid BarrackVehicleRequest request, @Valid String token) {
        return API.For(
                tokens.checkToken(token),
                barracks.getById(request.getBarrack_id())
        ).yield((check, barrack )->
                ifAuthorized(token, () -> Either.right(barrackVehicles.create(request)))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @DeleteMapping("/delete")
    private Object delete(@Valid @NotNull BarrackVehicleRequest request, @Valid String token) throws ParseException {
        return API.For(
                tokens.checkToken(token),
                barracks.getById(request.getBarrack_id())
        ).yield((check, barrack )->
                ifAuthorized(token, () -> Either.right(barrackVehicles.delete(request)))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @GetMapping("/{id}")
    private Object List(@PathVariable @Valid @NotNull UUID id, Pageable pageable, @Valid String token) {
        return API.For(
                tokens.checkToken(token)
        ).yield(user -> barrackVehicles.listUser(id, pageable))
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }
}
