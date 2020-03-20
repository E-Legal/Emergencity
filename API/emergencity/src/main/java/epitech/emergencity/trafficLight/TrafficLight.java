package epitech.emergencity.trafficLight;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
public class TrafficLight {
    private UUID id;
    @NotNull
    private String city;
    @NotNull
    private String x;
    @NotNull
    private String y;
    @NotNull
    private String z;
    private Boolean actif;
}
