package epitech.emergencity.barrack.services;

import epitech.emergencity.barrack.BarrackUser;
import epitech.emergencity.barrack.dto.BarrackUserRequest;
import epitech.emergencity.domain.Tables;
import epitech.emergencity.domain.tables.records.EmergencityBarrackRecord;
import epitech.emergencity.domain.tables.records.EmergencityBarrackUserRecord;
import epitech.emergencity.services.JOOQCrudUtils;
import epitech.emergencity.users.services.JOOQUsers;
import lombok.extern.slf4j.Slf4j;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static epitech.emergencity.domain.tables.EmergencityBarrack.EMERGENCITY_BARRACK;
import static epitech.emergencity.domain.tables.EmergencityBarrackUser.EMERGENCITY_BARRACK_USER;
import static epitech.emergencity.domain.tables.EmergencityUser.EMERGENCITY_USER;
import static org.jooq.impl.DSL.asterisk;
import static org.jooq.impl.DSL.count;

@Service
@Slf4j
public class JOOQBarrackUser implements BarrackUsers, JOOQCrudUtils {


    @Autowired
    DSLContext database;

    public static BarrackUser fromRecord(Record record) {
        return BarrackUser.builder()
                .barrack_id(record.getValue(EMERGENCITY_BARRACK_USER.BARRACK_ID))
                .user_id(record.getValue(EMERGENCITY_BARRACK_USER.USER_ID))
                .build();
    }

    @Override
    public BarrackUser create(UUID barrack_id, UUID user_id) {
        EmergencityBarrackUserRecord record = database.insertInto(EMERGENCITY_BARRACK_USER)
                .columns(EMERGENCITY_BARRACK_USER.BARRACK_ID, EMERGENCITY_BARRACK_USER.USER_ID)
                .values(barrack_id, user_id)
                .returning()
                .fetchOne();

        return BarrackUser.builder()
                .barrack_id(barrack_id)
                .user_id(user_id)
                .build();
    }

    @Override
    public Boolean delete(UUID barrack_id, UUID user_id) {
        return database.delete(EMERGENCITY_BARRACK_USER)
                .where(EMERGENCITY_BARRACK_USER.BARRACK_ID.eq(barrack_id).and(EMERGENCITY_BARRACK_USER.USER_ID.eq(user_id)))
                .execute() > 0;
    }

    @Override
    public PageImpl<Object> listUser(UUID id, Pageable pageable){
        Field<Integer> countField = count(asterisk()).over().as("count");
        Result<Record> records = database
                .select(
                        Tables.EMERGENCITY_USER.asterisk(),
                        Tables.EMERGENCITY_BARRACK.asterisk(),
                        countField
                ).from(EMERGENCITY_BARRACK_USER)
                .join(EMERGENCITY_USER)
                .on(EMERGENCITY_BARRACK_USER.USER_ID.eq(EMERGENCITY_USER.ID))
                .join(EMERGENCITY_BARRACK)
                .on(EMERGENCITY_BARRACK_USER.BARRACK_ID.eq(EMERGENCITY_BARRACK.ID))
                .where(EMERGENCITY_BARRACK_USER.BARRACK_ID.eq(id))
                .limit(pageable.getPageSize())
                .offset(Math.toIntExact(pageable.getOffset()))
                .fetch();

        List<epitech.emergencity.users.User> users = records
                .map(JOOQUsers::fromRecord);

        return new PageImpl<Object>(Collections.singletonList(users), pageable, records.isEmpty() ? 0 : records.getValue(0, countField));
    }

    @Override
    public PageImpl<Object> listBarrack(UUID id, Pageable pageable){
        Field<Integer> countField = count(asterisk()).over().as("count");
        Result<Record> records = database
                .select(
                        Tables.EMERGENCITY_BARRACK.asterisk(),
                        countField
                ).from(EMERGENCITY_BARRACK_USER)
                .join(EMERGENCITY_BARRACK)
                .on(EMERGENCITY_BARRACK_USER.BARRACK_ID.eq(EMERGENCITY_BARRACK.ID))
                .where(EMERGENCITY_BARRACK_USER.USER_ID.eq(id))
                .limit(pageable.getPageSize())
                .offset(Math.toIntExact(pageable.getOffset()))
                .fetch();

        List<epitech.emergencity.barrack.Barrack> users = records
                .map(JOOQBarrack::fromRecord);

        return new PageImpl<Object>(Collections.singletonList(users), pageable, records.isEmpty() ? 0 : records.getValue(0, countField));
    }

    @Override
    public Map<String, Field<?>> sortableField() {
        return Collections.unmodifiableMap(
                Stream.of(
                        new AbstractMap.SimpleEntry<>("barrack_id", EMERGENCITY_BARRACK_USER.BARRACK_ID)
                ).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));
    }

}
