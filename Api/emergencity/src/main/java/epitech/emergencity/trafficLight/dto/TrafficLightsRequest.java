package epitech.emergencity.trafficLight.dto;

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
public class TrafficLightsRequest {
    private UUID id;
    @NotNull
    private String city;
    @NotNull
    private String x;
    @NotNull
    private String y;
    @NotNull
    private String z;
}
