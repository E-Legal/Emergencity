package epitech.emergencity.profils;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
public class Profil {
    @NotNull
    private UUID id;
    @NotNull
    private UUID user_id;
    @NotNull
    private String first_name;
    @NotNull
    private String last_name;
    @NotNull
    private String job;


}
