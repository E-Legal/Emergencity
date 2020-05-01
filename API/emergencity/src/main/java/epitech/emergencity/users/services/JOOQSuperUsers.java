package epitech.emergencity.users.services;

import epitech.emergencity.domain.Tables;
import epitech.emergencity.domain.tables.EmergencityUser;
import epitech.emergencity.domain.tables.records.EmergencityUserRecord;
import epitech.emergencity.services.JOOQCrudUtils;
import epitech.emergencity.trafficLight.TrafficLight;
import epitech.emergencity.trafficLight.services.JOOQTrafficLight;
import epitech.emergencity.users.User;
import epitech.emergencity.users.dto.GradeRequest;
import epitech.emergencity.users.dto.UsersRequest;
import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static epitech.emergencity.domain.Tables.*;
import static epitech.emergencity.domain.tables.EmergencityBarrack.EMERGENCITY_BARRACK;
import static epitech.emergencity.domain.tables.EmergencityBarrackUser.EMERGENCITY_BARRACK_USER;
import static org.jooq.impl.DSL.asterisk;
import static org.jooq.impl.DSL.count;

@Service
@Slf4j
public class JOOQSuperUsers implements SuperUsers, JOOQCrudUtils {

    @Autowired
    DSLContext database;
    @Autowired
    Users users;


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
                .columns(EMERGENCITY_USER.NAME, EMERGENCITY_USER.PASSWORD,  EMERGENCITY_USER.CREATED_AT, EMERGENCITY_USER.ID)
                .values(request.getName(), request.getPassword(), now, id)
                .returning()
                .fetchOne();

        return User.builder()
                .id(id)
                .name(request.getName())
                .createdAt(now)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> listUser(Pageable pageable) {
        Field<Integer> countField = count(asterisk()).over().as("count");
        Result<Record> records = database
                .select(
                        EMERGENCITY_USER.asterisk(),
                        countField
                ).from(EMERGENCITY_USER)
                .limit(pageable.getPageSize())
                .offset(Math.toIntExact(pageable.getOffset()))
                .fetch();

        List<User> userList = records
                .map(JOOQUsers::fromRecord);

        return new PageImpl<>(userList, pageable, records.isEmpty() ? 0 : records.getValue(0, countField));
    }

    @Override
    public PageImpl<Object> listAdmin(Pageable pageable){
        Field<Integer> countField = count(asterisk()).over().as("count");
        Result<Record> records = database
                .select(
                        Tables.EMERGENCITY_USER.asterisk(),
                        Tables.EMERGENCITY_ADMIN.asterisk(),
                        countField
                ).from(EMERGENCITY_USER)
                .join(EMERGENCITY_ADMIN)
                .on(EMERGENCITY_USER.ID.eq(EMERGENCITY_ADMIN.USER_ID))
                .limit(pageable.getPageSize())
                .offset(Math.toIntExact(pageable.getOffset()))
                .fetch();

        List<epitech.emergencity.users.User> users = records
                .map(JOOQUsers::fromRecord);

        return new PageImpl<Object>(Collections.singletonList(users), pageable, records.isEmpty() ? 0 : records.getValue(0, countField));
    }

    @Override
    public PageImpl<Object> listSU(Pageable pageable){
        Field<Integer> countField = count(asterisk()).over().as("count");
        Result<Record> records = database
                .select(
                        Tables.EMERGENCITY_USER.asterisk(),
                        EMERGENCITY_SUPER_USER.asterisk(),
                        countField
                ).from(EMERGENCITY_USER)
                .join(EMERGENCITY_SUPER_USER)
                .on(EMERGENCITY_USER.ID.eq(EMERGENCITY_SUPER_USER.USER_ID))
                .limit(pageable.getPageSize())
                .offset(Math.toIntExact(pageable.getOffset()))
                .fetch();

        List<epitech.emergencity.users.User> users = records
                .map(JOOQUsers::fromRecord);

        return new PageImpl<Object>(Collections.singletonList(users), pageable, records.isEmpty() ? 0 : records.getValue(0, countField));
    }

    @Override
    public PageImpl<Object> listAlgo(Pageable pageable){
        Field<Integer> countField = count(asterisk()).over().as("count");
        Result<Record> records = database
                .select(
                        Tables.EMERGENCITY_USER.asterisk(),
                        EMERGENCITY_ALGO.asterisk(),
                        countField
                ).from(EMERGENCITY_USER)
                .join(EMERGENCITY_ALGO)
                .on(EMERGENCITY_USER.ID.eq(EMERGENCITY_ALGO.USER_ID))
                .limit(pageable.getPageSize())
                .offset(Math.toIntExact(pageable.getOffset()))
                .fetch();

        List<epitech.emergencity.users.User> users = records
                .map(JOOQUsers::fromRecord);

        return new PageImpl<Object>(Collections.singletonList(users), pageable, records.isEmpty() ? 0 : records.getValue(0, countField));
    }

}
