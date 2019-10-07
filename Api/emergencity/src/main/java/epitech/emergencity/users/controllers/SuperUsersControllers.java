package epitech.emergencity.users.controllers;

import epitech.emergencity.services.DomainError;
import epitech.emergencity.users.dto.GradeRequest;
import epitech.emergencity.users.dto.UsersRequest;
import epitech.emergencity.users.services.SuperUsers;
import epitech.emergencity.users.services.Users;
import io.vavr.API;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@EnableWebSecurity
@RestController
@RequestMapping("/SU")
@Slf4j
public class SuperUsersControllers {
    @Autowired
    Users users;
    @Autowired
    SuperUsers superUsers;

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
                superUsers.isSuperUser(token))
                .yield(user -> superUsers.createUser(request))
                .toEither(DomainError.Unauthorized(null))
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
