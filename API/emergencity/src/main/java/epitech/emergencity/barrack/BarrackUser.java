package epitech.emergencity.barrack;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;


@Data
@Builder
public class BarrackUser {
    @NotNull
    private UUID barrack_id;
    @NotNull
    private UUID user_id;
}
