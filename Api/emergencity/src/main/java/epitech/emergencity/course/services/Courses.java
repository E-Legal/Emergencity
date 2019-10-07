package epitech.emergencity.course.services;

import epitech.emergencity.course.Course;
import epitech.emergencity.course.dto.CourseRequest;
import io.vavr.control.Option;
import org.jooq.tools.json.ParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.json.Json;
import javax.json.bind.Jsonb;
import java.util.UUID;

public interface Courses {
    Course create(CourseRequest request);
    Option<Course> getById(UUID id);
    Option<Course> modById(UUID id, String course);
    Page<Course> listTCourse(Pageable pageable);
}
