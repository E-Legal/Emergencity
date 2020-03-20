package epitech.emergencity.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradeRequest {
    @NotNull
    private UUID id;
    @NotNull
    private Boolean admin;
    @NotNull
    private Boolean superuser;
    @NotNull
    private Boolean algorithm;
}
