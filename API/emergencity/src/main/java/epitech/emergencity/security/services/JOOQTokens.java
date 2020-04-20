package epitech.emergencity.security.services;

import epitech.emergencity.domain.tables.records.EmergencityTokenRecord;
import epitech.emergencity.security.Token;
import epitech.emergencity.services.JOOQCrudUtils;
import epitech.emergencity.services.RandomString;
import epitech.emergencity.users.User;
import epitech.emergencity.users.services.Users;
import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import org.jooq.*;
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

import static epitech.emergencity.domain.Tables.*;

@Service
@Slf4j
public class JOOQTokens implements Tokens, JOOQCrudUtils {
    @Autowired
    DSLContext database;
    @Autowired
    Users users;
    @Autowired
    Tokens tokens;


    public static Token fromRecord(EmergencityTokenRecord record) {
        return Token.builder()
                .id(record.getId())
                .user_id(record.getUserId())
                .token(record.getToken())
                .updateToken(record.getTokenUpdate())
                .endToken(record.getTokenEnd())
                .build();
    }


    public static Token fromRecord(Record record) {
        return Token.builder()
                .id(record.getValue(EMERGENCITY_USER.ID))
                .user_id(record.getValue(EMERGENCITY_TOKEN.USER_ID))
                .token(record.getValue(EMERGENCITY_TOKEN.TOKEN))
                .updateToken(record.getValue(EMERGENCITY_TOKEN.TOKEN_UPDATE))
                .endToken(record.getValue(EMERGENCITY_TOKEN.TOKEN_END))
                .build();
    }

    @Override
    @Transactional
    public Token create(UUID user_id) {
        RandomString session = new RandomString();
        String token = session.toString();
        token = token.replace("epitech.emergencity.services.RandomString@", "");
        OffsetDateTime now = OffsetDateTime.now();

        UUID id = UUID.randomUUID();
        EmergencityTokenRecord record = database.insertInto(EMERGENCITY_TOKEN)
                .columns(EMERGENCITY_TOKEN.ID, EMERGENCITY_TOKEN.USER_ID, EMERGENCITY_TOKEN.TOKEN, EMERGENCITY_TOKEN.TOKEN_UPDATE, EMERGENCITY_TOKEN.TOKEN_END)
                .values(id, user_id, token, now, now.plusMinutes(10))
                .returning()
                .fetchOne();

        return Token.builder()
                .id(id)
                .user_id(user_id)
                .token(token)
                .updateToken(now)
                .endToken(now.plusMinutes(10))
                .build();
    }

    @Override
    @Transactional
    public Option<Token> update(String oldToken) {
        RandomString session = new RandomString();
        String token = session.toString();
        token = token.replace("epitech.emergencity.services.RandomString@", "");
        OffsetDateTime now = OffsetDateTime.now();
        return Option.ofOptional(
                database.update(EMERGENCITY_TOKEN)
                        .set(EMERGENCITY_TOKEN.TOKEN, token)
                        .set(EMERGENCITY_TOKEN.TOKEN_UPDATE, now)
                        .set(EMERGENCITY_TOKEN.TOKEN_END, now.plusMinutes(10))
                        .where(EMERGENCITY_TOKEN.TOKEN.eq(oldToken))
                        .returning(EMERGENCITY_TOKEN.asterisk())
                        .fetchOptional()
                        .map(JOOQTokens::fromRecord)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Option<Token> checkToken(String token) {
        OffsetDateTime now = OffsetDateTime.now();
        return Option.ofOptional(
                database.selectFrom(EMERGENCITY_TOKEN)
                        .where(EMERGENCITY_TOKEN.TOKEN.eq(token).and(EMERGENCITY_TOKEN.TOKEN_END.greaterThan(now)))
                        .fetchOptional()
                        .map(JOOQTokens::fromRecord)
        );
    }

    @Override
    @Transactional
    public boolean is_admin(String token) {
        return database.fetchExists(database
                .selectFrom(EMERGENCITY_USER
                .join(EMERGENCITY_TOKEN)
                .on(EMERGENCITY_TOKEN.TOKEN.eq(token))
                .join(EMERGENCITY_ADMIN)
                .on(EMERGENCITY_ADMIN.USER_ID.eq(EMERGENCITY_TOKEN.USER_ID)))
                .where(EMERGENCITY_USER.ID.eq(EMERGENCITY_TOKEN.USER_ID))
        );
    }

    @Override
    @Transactional
    public boolean is_super_user(String token) {
        return database.fetchExists(database
                .selectFrom(EMERGENCITY_USER
                        .join(EMERGENCITY_TOKEN)
                        .on(EMERGENCITY_TOKEN.TOKEN.eq(token))
                        .join(EMERGENCITY_SUPER_USER)
                        .on(EMERGENCITY_SUPER_USER.USER_ID.eq(EMERGENCITY_TOKEN.USER_ID)))
                .where(EMERGENCITY_USER.ID.eq(EMERGENCITY_TOKEN.USER_ID))
        );
    }

    @Override
    @Transactional
    public boolean is_algo(String token) {
        return database.fetchExists(database
                .selectFrom(EMERGENCITY_USER
                        .join(EMERGENCITY_TOKEN)
                        .on(EMERGENCITY_TOKEN.TOKEN.eq(token))
                        .join(EMERGENCITY_ALGO)
                        .on(EMERGENCITY_ALGO.USER_ID.eq(EMERGENCITY_TOKEN.USER_ID)))
                .where(EMERGENCITY_USER.ID.eq(EMERGENCITY_TOKEN.USER_ID))
        );    }

    @Override
    public Map<String, Field<?>> sortableField() {
        return Collections.unmodifiableMap(
                Stream.of(
                        new AbstractMap.SimpleEntry<>("ID", EMERGENCITY_TOKEN.ID)
                ).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));
    }
}
