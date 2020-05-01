package epitech.emergencity.profils.controllers;


import epitech.emergencity.profils.dto.ProfilRequest;
import epitech.emergencity.profils.services.Profils;
import epitech.emergencity.security.Token;
import epitech.emergencity.services.DomainError;
import epitech.emergencity.users.dto.UsersRequest;
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
@RequestMapping("/user")
@Slf4j
public class ProfilControllers {

    @Autowired
    Users users;
    @Autowired
    epitech.emergencity.security.services.Tokens tokens;
    @Autowired
    Profils profils;

    private <T> Either<DomainError, T> ifAuthorized(Token token, ProfilRequest request, Supplier<Either<DomainError, T>> block) {
        log.info(token.getUser_id() + " " + request.getUser_id());
        if(token.getUser_id().equals(request.getUser_id()))
            return block.get();
        else
            return Either.left(DomainError.Unauthorized(null));
    }

    private <T> Either<DomainError, T> ifAuthorizedGet(Token token, UUID user_id, Supplier<Either<DomainError, T>> block) {
        if(token.getUser_id().equals(user_id))
            return block.get();
        else
            return Either.left(DomainError.Unauthorized(null));
    }

    @PostMapping("/{id}/profile/create")
    private Object create(@PathVariable @Valid @NotNull UUID id, @Valid ProfilRequest request, @Valid String token) {
        return API.For(
                tokens.checkToken(token)
        ).yield(check ->
                ifAuthorized(check, request, () -> Either.right(profils.create(request)))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    };

    @PostMapping("/{id}/profile/{pId}/update")
    private Object update(@PathVariable @Valid @NotNull UUID id, @PathVariable @Valid @NotNull UUID pId, @Valid ProfilRequest request, @Valid String token) {
        return API.For(
                tokens.checkToken(token)
        ).yield(check ->
                ifAuthorized(check, request, () -> Either.right(profils.update(pId, request).get()))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    };

    @GetMapping("/{id}/profile/{pId}")
    private Object get(@PathVariable @Valid @NotNull UUID id, @PathVariable @Valid @NotNull UUID pId, @Valid String token) {
        return API.For(
                tokens.checkToken(token)
        ).yield(check ->
                ifAuthorizedGet(check, id, () -> Either.right(profils.getByIdAndUserId(pId, id).get()))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    };

    @GetMapping("/{id}/profile/list")
    private Object List(Pageable pageable,@PathVariable @Valid @NotNull UUID id, @Valid String token) {
        return API.For(
                tokens.checkToken(token)
        ).yield(check ->
                ifAuthorizedGet(check, id, () -> Either.right(profils.listProfils(id, pageable)))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @DeleteMapping("/{id}/profile/{pId}/delete")
    private Object delete(@PathVariable @Valid @NotNull UUID id,@PathVariable @Valid @NotNull UUID pId, @Valid String token) throws ParseException {
        return API.For(
                tokens.checkToken(token),
                profils.getByIdAndUserId(pId, id)
        ).yield((check, profile) ->
                ifAuthorizedGet(check, id, () -> Either.right(profils.delete(pId)))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }
}
