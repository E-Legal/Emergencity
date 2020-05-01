package epitech.emergencity.profils.dto;

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
public class ProfilRequest {
    @NotNull
    private UUID user_id;
    @NotNull
    private String first_name;
    @NotNull
    private String last_name;
    @NotNull
    private String job;

}
