package epitech.emergencity.users.controllers;

import epitech.emergencity.security.services.Tokens;
import epitech.emergencity.services.DomainError;
import epitech.emergencity.users.dto.GradeRequest;
import epitech.emergencity.users.dto.UsersRequest;
import epitech.emergencity.users.services.SuperUsers;
import epitech.emergencity.users.services.Users;
import io.vavr.API;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        if (tokens.is_super_user(token))
            return block.get();
        else
            return Either.left(DomainError.Unauthorized(null));
    }
    @GetMapping("")
    private Object CheckSuperUser(@Valid String token) {
        return API.For(
                superUsers.isSuperUser(token))
                .yield(user -> user)
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
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

    @PostMapping("user/grade")
    private Object ChangeGradeOfUser(@Valid GradeRequest request, @Valid String token) {
        return API.For(
                superUsers.isSuperUser(token),
                users.get(request.getId()))
                .yield((admin, user ) -> superUsers.updateGrade(request))
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }
}
