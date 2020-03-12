package epitech.emergencity.barrack.services;

import epitech.emergencity.barrack.Barrack;
import epitech.emergencity.barrack.dto.BarrackRequest;
import epitech.emergencity.domain.tables.records.EmergencityBarrackRecord;
import epitech.emergencity.services.JOOQCrudUtils;
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

import static epitech.emergencity.domain.tables.EmergencityBarrack.EMERGENCITY_BARRACK;
import static org.jooq.impl.DSL.asterisk;
import static org.jooq.impl.DSL.count;

@Service
@Slf4j
public class JOOQBarrack implements Barracks, JOOQCrudUtils {


    @Autowired
    DSLContext database;

    public static Barrack fromRecord(Record record) {
        return Barrack.builder()
                .id(record.getValue(EMERGENCITY_BARRACK.ID))
                .name(record.getValue(EMERGENCITY_BARRACK.NAME))
                .city(record.getValue(EMERGENCITY_BARRACK.CITY))
                .build();
    }

    @Override
    public Barrack create(BarrackRequest request) {
        UUID id = UUID.randomUUID();
        EmergencityBarrackRecord record = database.insertInto(EMERGENCITY_BARRACK)
                .columns(EMERGENCITY_BARRACK.ID, EMERGENCITY_BARRACK.CITY, EMERGENCITY_BARRACK.NAME)
                .values(id, request.getCity(), request.getName())
                .returning()
                .fetchOne();

        return Barrack.builder()
                .id(id)
                .city(request.getCity())
                .name(request.getName())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Option<Barrack> getById(UUID id) {
        OffsetDateTime now = OffsetDateTime.now();
        return Option.ofOptional(
                database.selectFrom(EMERGENCITY_BARRACK)
                        .where(EMERGENCITY_BARRACK.ID.eq(id))
                        .fetchOptional()
                        .map(JOOQBarrack::fromRecord)
        );
    }


    @Override
    public Option<Barrack> modById(UUID id, BarrackRequest request) {
        return Option.ofOptional(
                database.update(EMERGENCITY_BARRACK)
                        .set(EMERGENCITY_BARRACK.NAME, request.getName())
                        .set(EMERGENCITY_BARRACK.CITY, request.getCity())
                        .where(EMERGENCITY_BARRACK.ID.eq(id))
                        .returning(EMERGENCITY_BARRACK.asterisk())
                        .fetchOptional()
                        .map(JOOQBarrack::fromRecord)
        );
    }
    @Override
    public Boolean delete(UUID id) {
        return database.delete(EMERGENCITY_BARRACK)
                .where(EMERGENCITY_BARRACK.ID.eq(id))
                .execute() > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Barrack> listBarracks(Pageable pageable) {
        Field<Integer> countField = count(asterisk()).over().as("count");
        Result<Record> records = database
                .select(
                        EMERGENCITY_BARRACK.asterisk(),
                        countField
                ).from(EMERGENCITY_BARRACK)
                .limit(pageable.getPageSize())
                .offset(Math.toIntExact(pageable.getOffset()))
                .fetch();

        List<Barrack> barracks = records
                .map(JOOQBarrack::fromRecord);

        return new PageImpl<>(barracks, pageable, records.isEmpty() ? 0 : records.getValue(0, countField));
    }

    @Override
    public Map<String, Field<?>> sortableField() {
        return Collections.unmodifiableMap(
                Stream.of(
                        new AbstractMap.SimpleEntry<>("name", EMERGENCITY_BARRACK.NAME)
                ).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));
    }
}
