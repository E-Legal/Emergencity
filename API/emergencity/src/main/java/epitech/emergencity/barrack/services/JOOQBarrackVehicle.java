package epitech.emergencity.barrack.services;

import epitech.emergencity.Vehicle.Vehicle;
import epitech.emergencity.Vehicle.services.JOOQVehicle;
import epitech.emergencity.barrack.BarrackUser;
import epitech.emergencity.barrack.BarrackVehicle;
import epitech.emergencity.barrack.dto.BarrackUserRequest;
import epitech.emergencity.barrack.dto.BarrackVehicleRequest;
import epitech.emergencity.domain.Tables;
import epitech.emergencity.domain.tables.records.EmergencityBarrackUserRecord;
import epitech.emergencity.domain.tables.records.EmergencityBarrackVehicleRecord;
import epitech.emergencity.services.JOOQCrudUtils;
import epitech.emergencity.users.services.JOOQUsers;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static epitech.emergencity.domain.Tables.EMERGENCITY_BARRACK_VEHICLE;
import static epitech.emergencity.domain.Tables.EMERGENCITY_VEHICLE;
import static epitech.emergencity.domain.tables.EmergencityBarrack.EMERGENCITY_BARRACK;
import static epitech.emergencity.domain.tables.EmergencityBarrackUser.EMERGENCITY_BARRACK_USER;
import static epitech.emergencity.domain.tables.EmergencityUser.EMERGENCITY_USER;
import static org.jooq.impl.DSL.asterisk;
import static org.jooq.impl.DSL.count;

@Service
@Slf4j
public class JOOQBarrackVehicle implements BarrackVehicles, JOOQCrudUtils {


    @Autowired
    DSLContext database;

    public static BarrackVehicle fromRecord(Record record) {
        return BarrackVehicle.builder()
                .barrack_id(record.getValue(EMERGENCITY_BARRACK_USER.BARRACK_ID))
                .vehicle_id(record.getValue(EMERGENCITY_BARRACK_USER.USER_ID))
                .build();
    }

    @Override
    public BarrackVehicle create(BarrackVehicleRequest request) {
        EmergencityBarrackVehicleRecord record = database.insertInto(EMERGENCITY_BARRACK_VEHICLE)
                .columns(EMERGENCITY_BARRACK_VEHICLE.BARRACK_ID, EMERGENCITY_BARRACK_VEHICLE.VEHICLE_ID)
                .values(request.getBarrack_id(), request.getVehicle_id())
                .returning()
                .fetchOne();

        return BarrackVehicle.builder()
                .barrack_id(request.getBarrack_id())
                .vehicle_id(request.getVehicle_id())
                .build();
    }

    @Override
    public Boolean delete(BarrackVehicleRequest request) {
        return database.delete(EMERGENCITY_BARRACK_VEHICLE)
                .where(EMERGENCITY_BARRACK_VEHICLE.BARRACK_ID.eq(request.getBarrack_id()).and(EMERGENCITY_BARRACK_VEHICLE.VEHICLE_ID.eq(request.getVehicle_id())))
                .execute() > 0;
    }

    @Override
    public PageImpl<Object> listUser(UUID id, Pageable pageable){
        Field<Integer> countField = count(asterisk()).over().as("count");
        Result<Record> records = database
                .select(
                        Tables.EMERGENCITY_VEHICLE.asterisk(),
                        Tables.EMERGENCITY_BARRACK.asterisk(),
                        countField
                ).from(EMERGENCITY_BARRACK_VEHICLE)
                .join(EMERGENCITY_VEHICLE)
                .on(EMERGENCITY_BARRACK_VEHICLE.VEHICLE_ID.eq(EMERGENCITY_VEHICLE.ID))
                .join(EMERGENCITY_BARRACK)
                .on(EMERGENCITY_BARRACK_VEHICLE.BARRACK_ID.eq(EMERGENCITY_BARRACK.ID))
                .where(EMERGENCITY_BARRACK_VEHICLE.BARRACK_ID.eq(id))
                .limit(pageable.getPageSize())
                .offset(Math.toIntExact(pageable.getOffset()))
                .fetch();

        List<Vehicle> vehicles = records
                .map(JOOQVehicle::fromRecord);

        return new PageImpl<Object>(Collections.singletonList(vehicles), pageable, records.isEmpty() ? 0 : records.getValue(0, countField));
    }

    @Override
    public Map<String, Field<?>> sortableField() {
        return Collections.unmodifiableMap(
                Stream.of(
                        new AbstractMap.SimpleEntry<>("barrack_id", EMERGENCITY_BARRACK_VEHICLE.BARRACK_ID)
                ).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));
    }

}
