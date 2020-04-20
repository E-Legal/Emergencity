package epitech.emergencity.barrack.controllers;


import epitech.emergencity.barrack.Barrack;
import epitech.emergencity.barrack.BarrackUser;
import epitech.emergencity.barrack.dto.BarrackRequest;
import epitech.emergencity.barrack.dto.BarrackUserRequest;
import epitech.emergencity.barrack.services.BarrackUsers;
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

@EnableWebSecurity
@RestController
@RequestMapping("/barrackUsers")
@Slf4j
public class BarrackUserControllers {

    @Autowired
    Users users;
    @Autowired
    Barracks barracks;
    @Autowired
    BarrackUsers barrackUsers;
    @Autowired
    Tokens tokens;

    private <T> Either<DomainError, T> ifAuthorized(String token, Supplier<Either<DomainError, T>> block) {
        if(tokens.is_admin(token))
            return block.get();
        else
            return Either.left(DomainError.Unauthorized(null));
    }

    @PostMapping("/new")
    private Object create(@Valid BarrackUserRequest request, @Valid String token) {
        return API.For(
                tokens.checkToken(token),
                barracks.getById(request.getBarrack_id())
        ).yield((check, barrack )->
                ifAuthorized(token, () -> Either.right(barrackUsers.create(request)))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @DeleteMapping("/delete")
    private Object delete(@Valid @NotNull BarrackUserRequest request, @Valid String token) throws ParseException {
        return API.For(
                tokens.checkToken(token),
                barracks.getById(request.getBarrack_id())
        ).yield((check, barrack )->
                ifAuthorized(token, () -> Either.right(barrackUsers.delete(request)))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @GetMapping("/{id}")
    private Object List(@PathVariable @Valid @NotNull UUID id, Pageable pageable, @Valid String token) {
        return API.For(
                tokens.checkToken(token)
        ).yield(user -> barrackUsers.listUser(id, pageable))
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }
}
