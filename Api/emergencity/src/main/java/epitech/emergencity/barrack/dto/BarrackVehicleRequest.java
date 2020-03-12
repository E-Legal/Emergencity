package epitech.emergencity.barrack.dto;

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
public class BarrackVehicleRequest {
    @NotNull
    private UUID barrack_id;
    @NotNull
    private UUID vehicle_id;
}
