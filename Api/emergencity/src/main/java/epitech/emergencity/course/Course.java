package epitech.emergencity.course;

import lombok.Builder;
import lombok.Data;

import java.lang.*;
import javax.json.Json;
import javax.json.bind.Jsonb;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
public class Course {
    private UUID id;
    @NotNull
    private String y_start;
    @NotNull
    private String x_start;
    @NotNull
    private String y_end;
    @NotNull
    private String x_end;
    private String course;
}
