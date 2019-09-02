package epitech.emergencity.trafficLight.services;

import epitech.emergencity.domain.tables.EmergencityTrafficlight;
import epitech.emergencity.domain.tables.records.EmergencityTrafficlightRecord;
import epitech.emergencity.domain.tables.records.EmergencityUserRecord;
import epitech.emergencity.services.JOOQCrudUtils;
import epitech.emergencity.trafficLight.TrafficLight;
import epitech.emergencity.users.User;
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

import static epitech.emergencity.domain.Tables.EMERGENCITY_TRAFFICLIGHT;
import static org.jooq.impl.DSL.asterisk;
import static org.jooq.impl.DSL.count;

@Service
public class JOOQTrafficLight implements TrafficLights, JOOQCrudUtils {

    @Autowired
    DSLContext database;
    @Autowired
    TrafficLights trafficLights;

    public static epitech.emergencity.trafficLight.TrafficLight fromRecord(EmergencityTrafficlightRecord record) {
        return epitech.emergencity.trafficLight.TrafficLight.builder()
                .id(record.getId())
                .city(record.getCity())
                .x(record.getX())
                .y(record.getY())
                .z(record.getZ())
                .build();
    }

    public static TrafficLight fromRecord(Record record) {
        return TrafficLight.builder()
                .id(record.getValue(EMERGENCITY_TRAFFICLIGHT.ID))
                .city(record.getValue(EMERGENCITY_TRAFFICLIGHT.CITY))
                .x(record.getValue(EMERGENCITY_TRAFFICLIGHT.X))
                .y(record.getValue(EMERGENCITY_TRAFFICLIGHT.Y))
                .z(record.getValue(EMERGENCITY_TRAFFICLIGHT.Z))
                .build();
    }

    @Override
    public Map<String, Field<?>> sortableField() {
        return Collections.unmodifiableMap(
                Stream.of(
                        new AbstractMap.SimpleEntry<>("city", EMERGENCITY_TRAFFICLIGHT.CITY)
                ).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));
    }

    @Override
    public TrafficLight create(String city, String x, String y, String z) {
        UUID id = UUID.randomUUID();
        EmergencityTrafficlightRecord record = database.insertInto(EMERGENCITY_TRAFFICLIGHT)
                .columns(EMERGENCITY_TRAFFICLIGHT.ID, EMERGENCITY_TRAFFICLIGHT.CITY, EMERGENCITY_TRAFFICLIGHT.X, EMERGENCITY_TRAFFICLIGHT.Y, EMERGENCITY_TRAFFICLIGHT.Z)
                .values(id, city, x, y, z)
                .returning()
                .fetchOne();

        return TrafficLight.builder()
                .id(id)
                .city(city)
                .x(x)
                .y(y)
                .z(z)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TrafficLight> listTrafficLight(Pageable pageable) {
        Field<Integer> countField = count(asterisk()).over().as("count");
        Result<Record> records = database
                .select(
                        EMERGENCITY_TRAFFICLIGHT.asterisk(),
                        countField
                ).from(EMERGENCITY_TRAFFICLIGHT)
                .limit(pageable.getPageSize())
                .offset(Math.toIntExact(pageable.getOffset()))
                .fetch();

        List<TrafficLight> trafficLights = records
                .map(JOOQTrafficLight::fromRecord);

        return new PageImpl<>(trafficLights, pageable, records.isEmpty() ? 0 : records.getValue(0, countField));
    }
}
