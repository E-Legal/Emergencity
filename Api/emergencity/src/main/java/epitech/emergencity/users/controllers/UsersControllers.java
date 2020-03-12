package epitech.emergencity.users.controllers;

import epitech.emergencity.services.DomainError;
import epitech.emergencity.users.dto.UsersRequest;
import epitech.emergencity.users.services.Users;
import io.vavr.API;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableWebSecurity
@RestController
@RequestMapping("")
@Slf4j
public class UsersControllers {

    @Autowired
    Users users;

    @PostMapping("/register")
    private Object create(
            @Valid UsersRequest request) {
        return users.create(request.getName(), request.getPassword()
        );
    }

    @GetMapping("/me/{tId}")
    private Object me(@PathVariable @Valid @NotNull String tId) {
        return API.For(
                users.checkToken(tId))
                .yield(user -> user)
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @PostMapping("/login")
    private Object token(@Valid UsersRequest request) {
        return API.For(
                users.tokenGen(request.getName(), request.getPassword()))
                .yield(user -> user)
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

}
