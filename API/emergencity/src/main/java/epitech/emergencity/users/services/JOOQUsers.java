package epitech.emergencity.users.services;

import epitech.emergencity.domain.tables.EmergencityUser;
import epitech.emergencity.domain.tables.records.EmergencityUserRecord;
import epitech.emergencity.services.DomainError;
import epitech.emergencity.services.JOOQCrudUtils;
import epitech.emergencity.services.RandomString;
import epitech.emergencity.users.User;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static epitech.emergencity.domain.Tables.EMERGENCITY_USER;
import static org.jooq.impl.DSL.asterisk;
import static org.jooq.impl.DSL.count;

@Service
@Slf4j
public class JOOQUsers implements Users, JOOQCrudUtils {

    @Autowired
    DSLContext database;
    @Autowired
    Users users;


    public static epitech.emergencity.users.User fromRecord(EmergencityUserRecord record) {
        return epitech.emergencity.users.User.builder()
                .id(record.getId())
                .name(record.getName())
                .createdAt(record.getCreatedAt())
                .updatedAt(record.getUpdateAt())
                .build();
    }


    public static epitech.emergencity.users.User fromRecord(Record record) {
        return User.builder()
                .id(record.getValue(EMERGENCITY_USER.ID))
                .name(record.getValue(EMERGENCITY_USER.NAME))
                .createdAt(record.getValue(EMERGENCITY_USER.CREATED_AT))
                .updatedAt(record.getValue(EMERGENCITY_USER.UPDATE_AT))
                .build();
    }

    @Override
    @Transactional
    public boolean validUser(String name, String password){
        Result<Record> records = database
                .select(
                        EMERGENCITY_USER.asterisk()
                )
                .from(EMERGENCITY_USER)
                .where(EMERGENCITY_USER.NAME.eq(name))
                .and(EMERGENCITY_USER.PASSWORD.eq(password))
                .fetch();

        return (records.isEmpty());
    }


    @Override
    @Transactional
    public User create(String name, String password) {
        OffsetDateTime now = OffsetDateTime.now();
        UUID id = UUID.randomUUID();
        EmergencityUserRecord record = database.insertInto(EMERGENCITY_USER)
                .columns(EMERGENCITY_USER.NAME, EMERGENCITY_USER.PASSWORD, EMERGENCITY_USER.CREATED_AT, EMERGENCITY_USER.ID)
                .values(name, password, now, id)
                .returning()
                .fetchOne();

        return User.builder()
                .id(id)
                .name(name)
                .createdAt(now)
                .build();
    }


    @Override
    @Transactional(readOnly = true)
    public Option<User> get(UUID id) {
        OffsetDateTime now = OffsetDateTime.now();
        return Option.ofOptional(
                database.selectFrom(EMERGENCITY_USER)
                        .where(EMERGENCITY_USER.ID.eq(id))
                        .fetchOptional()
                        .map(JOOQUsers::fromRecord)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Option<User> getUserLogin(String name, String password) {
        OffsetDateTime now = OffsetDateTime.now();
        return Option.ofOptional(
                database.selectFrom(EMERGENCITY_USER)
                        .where(EMERGENCITY_USER.NAME.eq(name).and(EMERGENCITY_USER.PASSWORD.eq(password)))
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
}
