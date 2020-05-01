package epitech.emergencity.profils.services;

import epitech.emergencity.Vehicle.Vehicle;
import epitech.emergencity.Vehicle.services.JOOQVehicle;
import epitech.emergencity.barrack.Barrack;
import epitech.emergencity.barrack.dto.BarrackRequest;
import epitech.emergencity.barrack.services.JOOQBarrack;
import epitech.emergencity.domain.Tables;
import epitech.emergencity.domain.tables.EmergencityUserProfil;
import epitech.emergencity.domain.tables.records.EmergencityBarrackRecord;
import epitech.emergencity.domain.tables.records.EmergencityUserProfilRecord;
import epitech.emergencity.profils.Profil;
import epitech.emergencity.profils.dto.ProfilRequest;
import epitech.emergencity.security.Token;
import epitech.emergencity.security.services.JOOQTokens;
import epitech.emergencity.services.JOOQCrudUtils;
import epitech.emergencity.services.RandomString;
import epitech.emergencity.trafficLight.TrafficLight;
import epitech.emergencity.trafficLight.services.JOOQTrafficLight;
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
import static epitech.emergencity.domain.Tables.EMERGENCITY_BARRACK_VEHICLE;
import static epitech.emergencity.domain.tables.EmergencityBarrack.EMERGENCITY_BARRACK;
import static org.jooq.impl.DSL.asterisk;
import static org.jooq.impl.DSL.count;

@Service
@Slf4j
public class JOOQProfils implements Profils, JOOQCrudUtils {

    @Autowired
    DSLContext database;

    public static Profil fromRecord(EmergencityUserProfilRecord record) {
        return Profil.builder()
                .id(record.getId())
                .user_id(record.getUserId())
                .first_name(record.getFirstName())
                .last_name(record.getLastName())
                .job(record.getJob())
                .build();
    }

    public static Profil fromRecord(Record record) {
        return Profil.builder()
                .id(record.getValue(EMERGENCITY_USER_PROFIL.ID))
                .user_id(record.getValue(EMERGENCITY_USER_PROFIL.USER_ID))
                .first_name(record.getValue(EMERGENCITY_USER_PROFIL.FIRST_NAME))
                .last_name(record.getValue(EMERGENCITY_USER_PROFIL.LAST_NAME))
                .job(record.getValue(EMERGENCITY_USER_PROFIL.JOB))
                .build();
    }

    @Override
    public Profil create(ProfilRequest request) {
        UUID id = UUID.randomUUID();
        EmergencityUserProfilRecord record = database.insertInto(EMERGENCITY_USER_PROFIL)
                .columns(EMERGENCITY_USER_PROFIL.ID, EMERGENCITY_USER_PROFIL.USER_ID, EMERGENCITY_USER_PROFIL.FIRST_NAME, EMERGENCITY_USER_PROFIL.LAST_NAME, EMERGENCITY_USER_PROFIL.JOB)
                .values(id, request.getUser_id(), request.getFirst_name(), request.getLast_name(), request.getJob())
                .returning()
                .fetchOne();

        return Profil.builder()
                .id(id)
                .user_id(request.getUser_id())
                .first_name(request.getFirst_name())
                .last_name(request.getLast_name())
                .job(request.getJob())
                .build();
    }

    @Override
    @Transactional
    public Option<Profil> update(UUID id, ProfilRequest request) {
        return Option.ofOptional(
                database.update(EMERGENCITY_USER_PROFIL)
                        .set(EMERGENCITY_USER_PROFIL.LAST_NAME , request.getLast_name())
                        .set(EMERGENCITY_USER_PROFIL.FIRST_NAME , request.getFirst_name())
                        .set(EMERGENCITY_USER_PROFIL.JOB , request.getJob())
                        .where(EMERGENCITY_USER_PROFIL.ID.eq(id).and(EMERGENCITY_USER_PROFIL.USER_ID.eq(request.getUser_id())))
                        .returning(EMERGENCITY_USER_PROFIL.asterisk())
                        .fetchOptional()
                        .map(JOOQProfils::fromRecord)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Option<Profil> getByIdAndUserId(UUID id, UUID user_id) {
        return Option.ofOptional(
                database.selectFrom(EMERGENCITY_USER_PROFIL)
                        .where(EMERGENCITY_USER_PROFIL.ID.eq(id).and(EMERGENCITY_USER_PROFIL.USER_ID.eq(user_id)))
                        .fetchOptional()
                        .map(JOOQProfils::fromRecord)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Profil> listProfils(UUID user_id, Pageable pageable) {
        Field<Integer> countField = count(asterisk()).over().as("count");
        Result<Record> records = database
                .select(
                        EMERGENCITY_USER_PROFIL.asterisk(),
                        countField
                ).from(EMERGENCITY_USER_PROFIL)
                .where(EMERGENCITY_USER_PROFIL.USER_ID.eq(user_id))
                .limit(pageable.getPageSize())
                .offset(Math.toIntExact(pageable.getOffset()))
                .fetch();

        List<Profil> profilList = records
                .map(JOOQProfils::fromRecord);

        return new PageImpl<>(profilList, pageable, records.isEmpty() ? 0 : records.getValue(0, countField));
    }

    @Override
    public Boolean delete(UUID id) {
        return database.delete(EMERGENCITY_USER_PROFIL)
                .where(EMERGENCITY_USER_PROFIL.ID.eq(id))
                .execute() >0;
    }

    @Override
    public Map<String, Field<?>> sortableField() {
        return Collections.unmodifiableMap(
                Stream.of(
                        new AbstractMap.SimpleEntry<>("USER_ID", EMERGENCITY_USER_PROFIL.USER_ID)
                ).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));
    }
}
