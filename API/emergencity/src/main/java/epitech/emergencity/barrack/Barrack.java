package epitech.emergencity.barrack;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
public class Barrack {
    private UUID id;
    @NotNull
    private String city;
    @NotNull
    private String name;
}
