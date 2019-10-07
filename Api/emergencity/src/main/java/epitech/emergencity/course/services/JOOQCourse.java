package epitech.emergencity.course.services;

import epitech.emergencity.course.Course;
import epitech.emergencity.course.dto.CourseRequest;
import epitech.emergencity.domain.tables.records.EmergencityDestinationRecord;
import epitech.emergencity.domain.tables.records.EmergencityTrafficlightRecord;
import epitech.emergencity.services.JOOQCrudUtils;
import epitech.emergencity.trafficLight.TrafficLight;
import epitech.emergencity.trafficLight.services.JOOQTrafficLight;
import epitech.emergencity.users.services.JOOQUsers;
import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.tools.json.JSONObject;
import org.jooq.tools.json.JSONParser;
import org.jooq.tools.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.json.Json;
import javax.json.bind.Jsonb;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static epitech.emergencity.domain.Tables.*;
import static epitech.emergencity.domain.Tables.EMERGENCITY_TRAFFICLIGHT;
import static org.jooq.impl.DSL.asterisk;
import static org.jooq.impl.DSL.count;

@Service
@Slf4j
public class JOOQCourse implements Courses, JOOQCrudUtils {

    @Autowired
    DSLContext database;

    public static epitech.emergencity.course.Course fromRecord(EmergencityDestinationRecord record) {
        return epitech.emergencity.course.Course.builder()
                .id(record.getId())
                .y_start(record.getYStart())
                .x_start(record.getXStart())
                .y_end(record.getYEnd())
                .x_end(record.getXEnd())
                .course(record.getCourse())
                .build();
    }

    public static Course fromRecord(Record record) {
        return Course.builder()
                .id(record.getValue(EMERGENCITY_DESTINATION.ID))
                .y_start(record.getValue(EMERGENCITY_DESTINATION.Y_START))
                .x_start(record.getValue(EMERGENCITY_DESTINATION.X_START))
                .y_end(record.getValue(EMERGENCITY_DESTINATION.Y_END))
                .x_end(record.getValue(EMERGENCITY_DESTINATION.X_END))
                .course(record.getValue(EMERGENCITY_DESTINATION.COURSE))
                .build();
    }

    @Override
    public Course create(CourseRequest request) {
        UUID id = UUID.randomUUID();
        String[] start = geocodeur(request.getStart());
        String[] end = geocodeur(request.getEnd());
        if (start == null | end == null) {
            log.info("error");
            return null;
        }
        EmergencityDestinationRecord record = database.insertInto(EMERGENCITY_DESTINATION)
                .columns(EMERGENCITY_DESTINATION.ID, EMERGENCITY_DESTINATION.Y_START, EMERGENCITY_DESTINATION.X_START, EMERGENCITY_DESTINATION.Y_END, EMERGENCITY_DESTINATION.X_END)
                .values(id, start[1], start[0], end[1], end[0])
                .returning()
                .fetchOne();

        return Course.builder()
                .id(id)
                .y_start(start[1])
                .x_start(start[0])
                .y_end(end[1])
                .x_end(end[0])
                .build();
    }

    private String[] geocodeur(String adress) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api-adresse.data.gouv.fr/search")
                .queryParam("q", adress);
        log.info("Uri ? {}", builder.toUriString());
        RestTemplate restTemplate = new RestTemplate();
        String resp = restTemplate.getForObject(builder.toUriString(), String.class);


        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(resp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        log.info("merde merde merde {}", json.get("features"));
        if (json.get("features").toString().compareTo("[]") == 0)
            return null;
        String test  = json.get("features").toString();
        test = test.substring(test.indexOf("\"x\":"));
        String x = (String) test.subSequence(4, test.indexOf(','));
        test = test.substring(test.indexOf("\"y\":"));
        String y = (String) test.subSequence(4, test.indexOf(','));

        log.info("Alors la x = {} et y = {}", x, y);
        String[] value = new String[2];
        value[0] = x;
        value[1] = y;
        log.info("Alors la x = {} et y = {}", value[0], value[1]);
        return (value);

    }

    @Override
    public Option<Course> getById(UUID id) {
        return Option.ofOptional(
                database.selectFrom(EMERGENCITY_DESTINATION)
                        .where(EMERGENCITY_DESTINATION.ID.eq(id))
                        .fetchOptional()
                        .map(JOOQCourse::fromRecord)
        );
    }

    @Override
    public Option<Course> modById(UUID id, String course) {
        return Option.ofOptional(
                database.update(EMERGENCITY_DESTINATION)
                        .set(EMERGENCITY_DESTINATION.COURSE, course)
                        .where(EMERGENCITY_DESTINATION.ID.eq(id))
                        .returning(EMERGENCITY_DESTINATION.asterisk())
                        .fetchOptional()
                        .map(JOOQCourse::fromRecord)
        );
    }

    @Override
    public Map<String, Field<?>> sortableField() {
        return Collections.unmodifiableMap(
                Stream.of(
                        new AbstractMap.SimpleEntry<>("id", EMERGENCITY_DESTINATION.ID)
                ).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Course> listTCourse(Pageable pageable) {
        Field<Integer> countField = count(asterisk()).over().as("count");
        Result<Record> records = database
                .select(
                        EMERGENCITY_DESTINATION.asterisk(),
                        countField
                ).from(EMERGENCITY_DESTINATION)
                .limit(pageable.getPageSize())
                .offset(Math.toIntExact(pageable.getOffset()))
                .fetch();

        List<Course> courses = records
                .map(JOOQCourse::fromRecord);

        return new PageImpl<>(courses, pageable, records.isEmpty() ? 0 : records.getValue(0, countField));
    }
}
