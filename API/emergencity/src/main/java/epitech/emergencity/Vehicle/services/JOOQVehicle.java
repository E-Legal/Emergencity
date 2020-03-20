package epitech.emergencity.Vehicle.services;

import epitech.emergencity.Vehicle.Vehicle;
import epitech.emergencity.Vehicle.dto.VehicleRequest;
import epitech.emergencity.domain.tables.records.EmergencityVehicleRecord;
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
import static epitech.emergencity.domain.tables.EmergencityVehicle.EMERGENCITY_VEHICLE;
import static org.jooq.impl.DSL.asterisk;
import static org.jooq.impl.DSL.count;

@Service
@Slf4j

public class JOOQVehicle implements Vehicles, JOOQCrudUtils {

    @Autowired
    DSLContext database;

    public static Vehicle fromRecord(Record record) {
        return Vehicle.builder()
                .id(record.getValue(EMERGENCITY_VEHICLE.ID))
                .model(record.getValue(EMERGENCITY_VEHICLE.MODEL))
                .registration(record.getValue(EMERGENCITY_VEHICLE.REGISTRATION))
                .build();
    }


    @Override
    public Vehicle create(VehicleRequest request) {
        UUID id = UUID.randomUUID();
        EmergencityVehicleRecord record = database.insertInto(EMERGENCITY_VEHICLE)
                .columns(EMERGENCITY_VEHICLE.ID, EMERGENCITY_VEHICLE.MODEL, EMERGENCITY_VEHICLE.REGISTRATION)
                .values(id, request.getModel(), request.getRegistration())
                .returning()
                .fetchOne();

        return Vehicle.builder()
                .id(id)
                .model(request.getModel())
                .registration(request.getRegistration())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Option<Vehicle> getById(UUID id) {
        OffsetDateTime now = OffsetDateTime.now();
        return Option.ofOptional(
                database.selectFrom(EMERGENCITY_VEHICLE)
                        .where(EMERGENCITY_VEHICLE.ID.eq(id))
                        .fetchOptional()
                        .map(JOOQVehicle::fromRecord)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Option<Vehicle> getByRegistration(String registration) {
        OffsetDateTime now = OffsetDateTime.now();
        return Option.ofOptional(
                database.selectFrom(EMERGENCITY_VEHICLE)
                        .where(EMERGENCITY_VEHICLE.REGISTRATION.eq(registration))
                        .fetchOptional()
                        .map(JOOQVehicle::fromRecord)
        );
    }


    @Override
    public Option<Vehicle> modById(UUID id, VehicleRequest request) {
        return Option.ofOptional(
                database.update(EMERGENCITY_VEHICLE)
                        .set(EMERGENCITY_VEHICLE.MODEL, request.getModel())
                        .set(EMERGENCITY_VEHICLE.REGISTRATION, request.getRegistration())
                        .where(EMERGENCITY_VEHICLE.ID.eq(id))
                        .returning(EMERGENCITY_VEHICLE.asterisk())
                        .fetchOptional()
                        .map(JOOQVehicle::fromRecord)
        );
    }

    @Override
    public Option<Vehicle> modByRegistration(String registration, VehicleRequest request) {
        return Option.ofOptional(
                database.update(EMERGENCITY_VEHICLE)
                        .set(EMERGENCITY_VEHICLE.MODEL, request.getModel())
                        .set(EMERGENCITY_VEHICLE.REGISTRATION, request.getRegistration())
                        .where(EMERGENCITY_VEHICLE.REGISTRATION.eq(registration))
                        .returning(EMERGENCITY_VEHICLE.asterisk())
                        .fetchOptional()
                        .map(JOOQVehicle::fromRecord)
        );
    }


    @Override
    public Boolean deleteById(UUID id) {
        return database.delete(EMERGENCITY_VEHICLE)
                .where(EMERGENCITY_VEHICLE.ID.eq(id))
                .execute() > 0;
    }

    @Override
    public Boolean deleteByRegistration(String reg) {
        return database.delete(EMERGENCITY_VEHICLE)
                .where(EMERGENCITY_VEHICLE.REGISTRATION.eq(reg))
                .execute() > 0;
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Vehicle> listVehicle(Pageable pageable) {
        Field<Integer> countField = count(asterisk()).over().as("count");
        Result<Record> records = database
                .select(
                        EMERGENCITY_VEHICLE.asterisk(),
                        countField
                ).from(EMERGENCITY_VEHICLE)
                .limit(pageable.getPageSize())
                .offset(Math.toIntExact(pageable.getOffset()))
                .fetch();

        List<Vehicle> vehicleList = records
                .map(JOOQVehicle::fromRecord);

        return new PageImpl<>(vehicleList, pageable, records.isEmpty() ? 0 : records.getValue(0, countField));
    }

    @Override
    public Map<String, Field<?>> sortableField() {
        return Collections.unmodifiableMap(
                Stream.of(
                        new AbstractMap.SimpleEntry<>("registration", EMERGENCITY_VEHICLE.REGISTRATION)
                ).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));
    }
}
