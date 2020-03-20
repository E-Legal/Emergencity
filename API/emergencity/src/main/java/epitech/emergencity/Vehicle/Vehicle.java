package epitech.emergencity.Vehicle;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
public class Vehicle {
    private UUID id;
    @NotNull
    private String model;
    @NotNull
    private String registration;
}
