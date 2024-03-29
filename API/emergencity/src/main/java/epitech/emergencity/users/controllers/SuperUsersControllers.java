package epitech.emergencity.users.controllers;

import epitech.emergencity.barrack.dto.BarrackUserRequest;
import epitech.emergencity.security.services.Tokens;
import epitech.emergencity.services.DomainError;
import epitech.emergencity.users.dto.GradeRequest;
import epitech.emergencity.users.dto.UsersRequest;
import epitech.emergencity.users.services.SuperUsers;
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
@RequestMapping("/SU")
@Slf4j
public class SuperUsersControllers {
    @Autowired
    Users users;
    @Autowired
    SuperUsers superUsers;
    @Autowired
    Tokens tokens;



    private <T> Either<DomainError, T> ifAuthorized(String token, Supplier<Either<DomainError, T>> block) {
        if (tokens.is_super_user(token) || tokens.is_admin(token))
            return block.get();
        else
            return Either.left(DomainError.Unauthorized(null));
    }

    @PostMapping("user/create")
    private Object createUser(@Valid UsersRequest request, @Valid String token) {

        return API.For(
                tokens.checkToken(token)
        ).yield(check ->
                ifAuthorized(token, () -> Either.right(superUsers.createUser(request)))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @GetMapping("/user/list")
    private Object List(Pageable pageable, @Valid String token) {
        return API.For(
                tokens.checkToken(token)
        ).yield(check ->
                ifAuthorized(token, () -> Either.right(superUsers.listUser(pageable)))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @DeleteMapping("/user/{id}")
    private Object delete(@PathVariable @Valid @NotNull UUID id, @Valid String token) throws ParseException {
        return API.For(
                tokens.checkToken(token),
                users.get(id)
        ).yield((check, barrack )->
                ifAuthorized(token, () -> Either.right(superUsers.deleteUserById(id)))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }


    @GetMapping("/user/list/admin")
    private Object ListAdmin(Pageable pageable, @Valid String token) {
        return API.For(
                tokens.checkToken(token)
        ).yield(check ->
                ifAuthorized(token, () -> Either.right(superUsers.listAdmin(pageable)))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }
    @GetMapping("/user/list/algo")
    private Object ListAlgo(Pageable pageable, @Valid String token) {
        return API.For(
                tokens.checkToken(token)
        ).yield(check ->
                ifAuthorized(token, () -> Either.right(superUsers.listAlgo(pageable)))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @GetMapping("/user/list/SU")
    private Object ListSU(Pageable pageable, @Valid String token) {
        return API.For(
                tokens.checkToken(token)
        ).yield(check ->
                ifAuthorized(token, () -> Either.right(superUsers.listSU(pageable)))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    /*@PostMapping("user/grade")
    private Object ChangeGradeOfUser(@Valid GradeRequest request, @Valid String token) {
        return API.For(

                superUsers.isSuperUser(token),
                users.get(request.getId()))
                .yield((admin, user ) -> superUsers.updateGrade(request))
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }*/
}
