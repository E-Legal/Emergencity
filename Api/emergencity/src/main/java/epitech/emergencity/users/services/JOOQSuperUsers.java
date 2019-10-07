package epitech.emergencity.users.services;

import epitech.emergencity.domain.tables.records.EmergencityUserRecord;
import epitech.emergencity.services.JOOQCrudUtils;
import epitech.emergencity.users.User;
import epitech.emergencity.users.dto.GradeRequest;
import epitech.emergencity.users.dto.UsersRequest;
import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static epitech.emergencity.domain.Tables.EMERGENCITY_USER;

@Service
@Slf4j
public class JOOQSuperUsers implements SuperUsers, JOOQCrudUtils {

    @Autowired
    DSLContext database;
    @Autowired
    Users users;

    @Override
    @Transactional(readOnly = true)
    public Option<User> isSuperUser(String token) {
        OffsetDateTime now = OffsetDateTime.now();
        return Option.ofOptional(
                database.selectFrom(EMERGENCITY_USER)
                        .where(EMERGENCITY_USER.TOKEN.eq(token).and(EMERGENCITY_USER.TOKEN_END.greaterThan(now).and(EMERGENCITY_USER.SUPERUSER.eq(true))))
                        .fetchOptional()
                        .map(JOOQUsers::fromRecord)
        );
    }

    @Override
    public Map<String, Field<?>> sortableField() {
        return Collections.unmodifiableMap(
                Stream.of(
                        new AbstractMap.SimpleEntry<>("name", EMERGENCITY_USER.NAME)
                ).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));
    }

    public User createUser(UsersRequest request) {
        OffsetDateTime now = OffsetDateTime.now();
        UUID id = UUID.randomUUID();
        EmergencityUserRecord record = database.insertInto(EMERGENCITY_USER)
                .columns(EMERGENCITY_USER.NAME, EMERGENCITY_USER.PASSWORD, EMERGENCITY_USER.ADMIN, EMERGENCITY_USER.SUPERUSER, EMERGENCITY_USER.ALGORITHM, EMERGENCITY_USER.CREATED_AT, EMERGENCITY_USER.ID, EMERGENCITY_USER.ADMIN)
                .values(request.getName(), request.getPassword(), request.getAdmin(), request.getSuperuser(), request.getAlgorithm(), now, id, false)
                .returning()
                .fetchOne();

        return User.builder()
                .id(id)
                .name(request.getName())
                .admin(request.getAdmin())
                .superuser(request.getSuperuser())
                .algorithm(request.getAlgorithm())
                .createdAt(now)
                .build();
    }

    @Override
    @Transactional
    public Option<User> updateGrade(GradeRequest request) {
        return Option.ofOptional(
                database.update(EMERGENCITY_USER)
                        .set(EMERGENCITY_USER.ADMIN , request.getAdmin())
                        .set(EMERGENCITY_USER.SUPERUSER , request.getSuperuser())
                        .set(EMERGENCITY_USER.ALGORITHM , request.getAlgorithm())
                        .where(EMERGENCITY_USER.ID.eq(request.getId()))
                        .returning(EMERGENCITY_USER.asterisk())
                        .fetchOptional()
                        .map(JOOQUsers::fromRecord)
        );
    }
}
