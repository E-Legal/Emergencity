package epitech.emergencity.security.controllers;

import epitech.emergencity.security.services.Tokens;
import epitech.emergencity.services.DomainError;
import epitech.emergencity.users.dto.UsersRequest;
import epitech.emergencity.users.services.Users;
import io.vavr.API;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableWebSecurity
@RestController
@RequestMapping("/login")
@Slf4j
public class TokenControllers {

    @Autowired
    Users users;
    @Autowired
    epitech.emergencity.security.services.Tokens tokens;
    @PostMapping("")
    private Object token(@Valid UsersRequest request) {
        return API.For(
                users.getUserLogin(request.getName(), request.getPassword()))
                .yield(user -> tokens.create(user.getId()))
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @PostMapping("/update")
    private Object update_token(@Valid String token) {
        return API.For(
                tokens.update(token))
                .yield(tok -> tok)
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

}
