package epitech.emergencity.course.controllers;

import epitech.emergencity.course.Course;
import epitech.emergencity.course.dto.CourseRequest;
import epitech.emergencity.course.services.Courses;
import epitech.emergencity.security.services.Tokens;
import epitech.emergencity.services.DomainError;
import epitech.emergencity.users.services.Users;
import io.vavr.API;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;
import org.jooq.tools.json.JSONParser;
import org.jooq.tools.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.client.RestTemplate;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.bind.Jsonb;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Array;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableWebSecurity
@RestController
@RequestMapping("/courses")
@Slf4j
public class CourseControllers {

    @Autowired
    Courses courses;
    @Autowired
    Users users;
    @Autowired
    Tokens tokens;

    private <T> Either<DomainError, T> ifAuthorized(String token, Supplier<Either<DomainError, T>> block) {
        if(tokens.is_admin(token) || tokens.is_super_user(token))
            return block.get();
        else
            return Either.left(DomainError.Unauthorized(null));
    }

    @PostMapping("/new")
    private Object create(@Valid CourseRequest request, @Valid String token) {
        return API.For(
                tokens.checkToken(token)
        ).yield(user -> {
            Course e = courses.create(request);
            if (e == null) {
                DomainError r = (DomainError.BadRequest(null));
                return (ResponseEntity.status(r.getStatus()).body(r.getData()));
            }
            return e;
        })
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @GetMapping ("/{id}")
    private Object get(@PathVariable @Valid @NotNull UUID id, @Valid String token) throws ParseException {
        return API.For(
                tokens.checkToken(token),
                courses.getById(id)
        ).yield((user, course) -> course)
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @PostMapping ("/{id}")
    private Object modifyCourse(@PathVariable @Valid @NotNull UUID id, @Valid String route, @Valid String time, @Valid String distance, @Valid String token) {
        return API.For(
                tokens.checkToken(token),
                courses.modById(id, route, distance, time)
        ).yield((user, course) -> course)
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @GetMapping("")
    private Object List(Pageable pageable, @Valid String token) {
        return API.For(
                tokens.checkToken(token)
        ).yield(user -> courses.listTCourse(pageable))
                .toEither(DomainError.Unauthorized(null))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

    @DeleteMapping("/{id}")
    private Object delete(@PathVariable @Valid @NotNull UUID id, @Valid String token) throws ParseException {
        return API.For(
                tokens.checkToken(token),
                courses.getById(id)
        ).yield((check, course) ->
                ifAuthorized(token, () -> Either.right(courses.delete(id)))
        ).getOrElse(Either.left(DomainError.NotFound(null)))
                .fold(r -> ResponseEntity.status(r.getStatus()).body(r.getData()), u -> u);
    }

}
